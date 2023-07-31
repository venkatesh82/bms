package com.sample.bms.service;

import com.sample.bms.domain.BookedSeat;
import com.sample.bms.domain.Seat;
import com.sample.bms.repository.BookedSeatRepository;
import com.sample.bms.repository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final BookedSeatRepository bookedSeatRepository;

    public SeatService(SeatRepository seatRepository, BookedSeatRepository bookedSeatRepository) {
        this.seatRepository = seatRepository;
        this.bookedSeatRepository = bookedSeatRepository;
    }

    public List<Seat> findAvailableSeatsByShowId(Long showId) {
        return seatRepository.findAvailableSeatsByShowId(showId);
    }

    public Optional<Seat> findById(Long id) {
        return seatRepository.findById(id);
    }

    @Transactional
    public void saveBookedSeat(BookedSeat bookedSeat) {
        bookedSeatRepository.save(bookedSeat);
    }

    @Transactional
    public void save(Seat seat) {
        seatRepository.save(seat);
    }

 }
