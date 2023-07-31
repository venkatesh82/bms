package com.sample.bms.service;

import com.sample.bms.domain.Show;
import com.sample.bms.domain.Theater;
import com.sample.bms.repository.ShowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }


    @Cacheable(value = "showsById", key = "#id")
    public Show findById(Long id) {
        log.info("Searching for Show with ID {} ...", id);
        return showRepository.findById(id).orElseThrow(() -> new RuntimeException("Requested show not found"));
    }

    @Cacheable(value = "showsByTheaterAndDate", key = "{#theaterId, #date}")
    public List<Show> findByTheaterAndShowDate(Theater theater, LocalDate showDate) {
        return showRepository.findByTheaterAndDate(theater, showDate);
    }

    @CachePut(value = "showsById", key = "#result.id")
    public Show saveShow(Show show) {
        return showRepository.save(show);
    }

    @CacheEvict(value = "showsById", key = "#id")
    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }

    public List<String> findByDate(LocalDate date) {
        return showRepository.findDistinctShowNamesByDate(date);
    }
}
