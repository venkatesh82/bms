package com.sample.bms.repository;

import com.sample.bms.domain.SeatRow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRowRepository extends JpaRepository<SeatRow, Long> {
}
