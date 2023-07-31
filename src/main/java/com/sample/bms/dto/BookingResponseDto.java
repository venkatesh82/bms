package com.sample.bms.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class BookingResponseDto {
    private String bookingNbr;
    private String bookingStatus;
    private String bookingErrorMessage;
}
