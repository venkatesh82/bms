package com.sample.bms.web.rest;

import com.sample.bms.domain.Show;
import com.sample.bms.domain.Theater;
import com.sample.bms.service.ShowService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowsApi {
    private final ShowService showService;

    public ShowsApi(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable Long id) {
        Show show = showService.findById(id);
        return ResponseEntity.ok(show);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<String>> getShowsByTheaterAndDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<String> shows = showService.findByDate(date);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/theater/{theaterId}/date/{date}")
    public ResponseEntity<List<Show>> getShowsByDate(
            @PathVariable Long theaterId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Theater theater = new Theater();
        theater.setId(theaterId);
        List<Show> shows = showService.findByTheaterAndShowDate(theater, date);
        return ResponseEntity.ok(shows);
    }

    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody Show show) {
        Show savedShow = showService.saveShow(show);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShow);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Show> updateShow(@PathVariable Long id, @RequestBody Show show) {
        Show existingShow = showService.findById(id);
        existingShow.setMovie(show.getMovie());
        existingShow.setTheater(show.getTheater());
        existingShow.setShowTime(show.getShowTime());
        existingShow.setDate(show.getDate());
        Show updatedShow = showService.saveShow(existingShow);
        return ResponseEntity.ok(updatedShow);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}
