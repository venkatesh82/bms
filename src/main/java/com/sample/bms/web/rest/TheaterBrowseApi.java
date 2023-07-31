package com.sample.bms.web.rest;

import com.sample.bms.dto.TheaterDto;
import com.sample.bms.dto.TheaterShowsDto;
import com.sample.bms.service.TheaterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/theater")
@Slf4j
public class TheaterBrowseApi {

    private final TheaterService theaterService;

    public TheaterBrowseApi(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @GetMapping("/browse")
    @Operation(summary = "Get theaters by city, movie title, and date")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved theaters")
    public ResponseEntity<List<TheaterDto>> getTheaters(@Parameter(description = "City name", required = true, example = "Hyderabad")
                                                        @NotBlank @RequestParam(name = "city") String city,
                                                        @Parameter(description = "Movie Title", required = true, example = "Mission Impossible")
                                                        @NotBlank @RequestParam(name = "movieTitle") String movieTitle,
                                                        @Parameter(description = "Date", required = true, example = "2023-07-22")
                                                        @NotBlank @RequestParam(name = "date") LocalDate date) {
        log.info("city ={}, movie ={} date = {}", city, movieTitle, date);
        List<TheaterDto> theaterList = theaterService.getTheaters(city, movieTitle, date);
        return ResponseEntity.ok(theaterList);
    }

    @GetMapping("browse-by-offer")
    @Operation(summary = "Get theaters and shows by offer, city, and date")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved theaters and shows")
    public ResponseEntity<List<TheaterShowsDto>> getTheatersByOffer(@NotBlank @RequestParam(name = "offer") String offer,
                                                                    @NotBlank @RequestParam(name = "city") String city,
                                                                    @NotBlank @RequestParam(name = "date") LocalDate date) {
        log.info("offer ={}, city ={} date = {}", offer, city, date);
        List<TheaterShowsDto> theaterList = theaterService.getTheatersAndShowsByOffer(offer, city, date);
        return ResponseEntity.ok(theaterList);
    }
}
