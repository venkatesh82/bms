package com.sample.bms.dto;

import lombok.AllArgsConstructor;

import java.util.List;


@AllArgsConstructor
public class TheaterShowsDto {
    private String theaterName;
    private List<ShowDto> shows;
}
