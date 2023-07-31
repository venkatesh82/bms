package com.sample.bms.web.rest;

import com.sample.bms.dto.BookingRequestDto;
import com.sample.bms.dto.BookingResponseDto;
import com.sample.bms.service.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@Slf4j
public class BookingApi {

    private final BookingService bookingService;

    public BookingApi(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> bookTickets(@Valid @RequestBody BookingRequestDto request) {
        BookingResponseDto bookingResponseDto = bookingService.bookTickets(request);
        return ResponseEntity.ok(bookingResponseDto);
    }
}
