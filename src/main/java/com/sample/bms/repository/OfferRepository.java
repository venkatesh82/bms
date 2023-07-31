package com.sample.bms.repository;

import com.sample.bms.domain.Offer;

import com.sample.bms.domain.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByTheater(Theater theater);
}

