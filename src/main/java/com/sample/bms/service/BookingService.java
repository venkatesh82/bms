package com.sample.bms.service;

import com.sample.bms.domain.*;
import com.sample.bms.dto.BookingRequestDto;
import com.sample.bms.dto.BookingResponseDto;
import com.sample.bms.event.BookingEvent;
import com.sample.bms.repository.BookingRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@Slf4j
public class BookingService {

    private final BlockingQueue<BookingEvent> bookingQueue = new LinkedBlockingQueue<>();
    private final ShowService showService;
    private final SeatService seatService;
    private final BookingRepository bookingRepository;
    private final OfferService offerService;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    public BookingService(ShowService showService, SeatService seatService, BookingRepository bookingRepository, OfferService offerService, CustomerService customerService, PaymentService paymentService) {
        this.showService = showService;
        this.seatService = seatService;
        this.bookingRepository = bookingRepository;
        this.offerService = offerService;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    public void startProcessing() {
        new Thread(this::processBookingEvents).start();
    }

    @PostConstruct
    public void init() {
        startProcessing();
    }

    @Transactional
    public BookingResponseDto bookTickets(BookingRequestDto request) {
        log.info("Booking Request Received:{}", request);
        BookingResponseDto bookingResponseDto;
        try {
            // Cached in showService
            log.info("Finding show for given show id...");
            Show show = showService.findById(request.getShowId());

            log.info("Getting all available seats...");
            List<Seat> availableSeats = seatService.findAvailableSeatsByShowId(show.getId());

            // Cached in customerService
            log.info("Finding customer for given customer id...");
            Customer customer = customerService.findById(request.getCustomerId());

            log.info("Verifying whether provided seats are available...");
            List<Seat> selectedSeats = new ArrayList<>();
            for (Long seatId : request.getSeatIds()) {
                Optional<Seat> seatOptional = seatService.findById(seatId);
                if (seatOptional.isEmpty()) {
                    throw new IllegalArgumentException("Seat " + seatId + " not found.");
                }

                Seat seat = seatOptional.get();

                if (!isSeatAvailable(seat, availableSeats)) {
                    throw new IllegalStateException("Seat " + seatId + " is not available.");
                }
                selectedSeats.add(seat);
            }

            List<Offer> offers = new ArrayList<>();

            if (request.getOfferIds() != null && request.getOfferIds().size() > 0) {
                log.info("Fetching offers..");
                offers = fetchOffers(request.getOfferIds());
            }

            Booking booking = lockSeatsAndCreateBooking(show, customer, selectedSeats, offers);

            log.info("Publishing Booking Event");
            BookingEvent bookingEvent = new BookingEvent(booking, selectedSeats);
            if (bookingQueue.offer(bookingEvent)) {
                log.info("Booking Event Published Successfully.");
            }
            bookingResponseDto = new BookingResponseDto(booking.getBookingNbr(), "SUCCESS", "");
        } catch (Exception ex) {
            log.error("An error occurred during booking. Reason: {}", ex.getMessage());
            bookingResponseDto = new BookingResponseDto("", "FAILURE", ex.getLocalizedMessage());
        }
        return bookingResponseDto;
    }

    private Booking lockSeatsAndCreateBooking(Show show, Customer customer, List<Seat> selectedSeats, List<Offer> offers) {
        LocalDateTime now = LocalDateTime.now();
        log.info("Locking Seats...");
        for (Seat seat : selectedSeats) {
            seat.setLockedAt(now);
            seatService.save(seat);
        }

        log.info("Calculating total price...");
        BigDecimal totalPrice = calculateTotalPrice(selectedSeats, offers);

        Booking booking = new Booking();
        booking.setShow(show);
        booking.setCustomer(customer);
        booking.setBookingNbr(generateBookingNumber());
        booking.setBookingTime(now);
        booking.setTotalPrice(totalPrice);
        log.info("Saving initial booking details...");
        return bookingRepository.save(booking);
    }

    private void processBookingEvents() {
        while (true) {
            try {
                BookingEvent bookingEvent = bookingQueue.take();
                log.info("===================================");
                log.info("Booking Event Received {}", bookingEvent);
                log.info("===================================");
                Booking booking = bookingEvent.getBooking();

                // Simulate payment processing (PaymentService implementation)
                boolean paymentSuccess = paymentService.processPayment(booking, booking.getTotalPrice());

                if (paymentSuccess) {
                    finalizeBooking(booking, bookingEvent.getSeats());
                } else {
                    // Payment failed, so releasing the locked seats
                    releaseSeats(bookingEvent.getSeats());
                    log.error("Payment failed for booking {}. Seats have been released.", booking.getBookingNbr());
                }
            } catch (InterruptedException e) {
                log.error("An error occurred during booking event processing. Reason: {}", e.getMessage());
            }
        }
    }

    private String generateBookingNumber() {
        return UUID.randomUUID().toString();
    }

    private BigDecimal calculateTotalPrice(List<Seat> selectedSeats, List<Offer> offers) {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Seat seat : selectedSeats) {
            totalPrice = totalPrice.add(seat.getPrice());
        }

        for (Offer offer : offers) {
            if (offer.getDiscountType() == DiscountType.PERCENTAGE) {
                double discount = offer.getDiscountAmount();
                totalPrice = totalPrice.subtract((totalPrice.multiply(new BigDecimal(discount))).divide(BigDecimal.valueOf(100),2, RoundingMode.DOWN));
            } else if (offer.getDiscountType() == DiscountType.FLAT) {
                totalPrice = totalPrice.subtract(BigDecimal.valueOf(offer.getDiscountAmount()));
            }
        }
        return totalPrice;
    }

    private List<Offer> fetchOffers(List<Long> offersIds) {
        List<Offer> offers = new ArrayList<>();
        for (Long id : offersIds) {
            offers.add(offerService.findById(id));
        }
        return offers;
    }

    private boolean isSeatAvailable(Seat seat, Iterable<Seat> availableSeats) {
        for (Seat availableSeat : availableSeats) {
            if (availableSeat.getId().equals(seat.getId())) {
                return true;
            }
        }
        return false;
    }

    public void finalizeBooking(Booking booking, List<Seat> seats) {
        log.info("Finalizing Booking..");
        for (Seat seat : seats) {
            seat.setLockedAt(null);
            seat.setBooked(true);
            seatService.save(seat);

            BookedSeat bookedSeat = new BookedSeat();
            bookedSeat.setBooking(booking);
            bookedSeat.setSeat(seat);
            seatService.saveBookedSeat(bookedSeat);
        }
        log.info("Booking {} successfully finalized.", booking.getBookingNbr());
    }

    private void releaseSeats(List<Seat> seats) {
        log.info("Releasing Seats...");
        for (Seat seat : seats) {
            seat.setLockedAt(null);
            seatService.save(seat);
        }
    }
}
