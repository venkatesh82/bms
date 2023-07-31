package com.sample.bms.repository;

import com.sample.bms.domain.Booking;
import com.sample.bms.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(b) FROM Booking b " +
            "WHERE b.customer = :customer " +
            "AND YEAR(b.bookingTime) = YEAR(CURRENT_DATE) " +
            "AND MONTH(b.bookingTime) = MONTH(CURRENT_DATE)")
    int getBookingCountForCurrentMonthByCustomer(Customer customer);
}
