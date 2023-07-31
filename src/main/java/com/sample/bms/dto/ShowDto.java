package com.sample.bms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ShowDto {

    private String movieTitle;
    private LocalTime showTime;
    private LocalDate showDate;

    public ShowDto() {

    }
}
