package com.sample.bms.service;

import com.sample.bms.domain.Offer;
import com.sample.bms.domain.Theater;
import com.sample.bms.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    @Cacheable(value = "offersById", key = "#id")
    public Offer findById(Long id){
        return offerRepository.findById(id).orElseThrow(() -> new RuntimeException("Offer not found"));
    }

    @Cacheable(value = "offersByTheater", key = "#theater.id")
    public List<Offer> findOffersByTheater(Theater theater){
        log.info("Searching offer by theater {}", theater.getName());
        return offerRepository.findByTheater(theater);
    }
}
