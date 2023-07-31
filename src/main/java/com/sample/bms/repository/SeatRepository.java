package com.sample.bms.repository;

import com.sample.bms.domain.Seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

/*    @Query("SELECT s FROM Seat s " +
            "LEFT JOIN BookedSeat bs ON s = bs.seat AND bs.booking.show.id = :showId " +
            "WHERE s.show.id = :showId AND " +
            "CASE WHEN bs.id IS NOT NULL THEN true ELSE false END = false")
    List<Seat> findAvailableSeatsByShowId(@Param("showId") Long showId);*/

//    @Query("SELECT s FROM Seat s " +
//            "LEFT JOIN BookedSeat bs ON s = bs.seat AND bs.booking.show.id = :showId " +
//            "LEFT JOIN bs.booking b " +
//            "WHERE s.show.id = :showId AND " +
//            "CASE WHEN b.id IS NOT NULL THEN true ELSE false END = false")
//    List<Seat> findAvailableSeatsByShowId(@Param("showId") Long showId);

    @Query("SELECT s FROM Seat s " +
            "WHERE s.show.id = :showId AND s.lockedAt IS NULL AND " +
            "s.id NOT IN (SELECT bs.seat.id FROM BookedSeat bs WHERE bs.booking.show.id = :showId)")
    List<Seat> findAvailableSeatsByShowId(@Param("showId") Long showId);


}
