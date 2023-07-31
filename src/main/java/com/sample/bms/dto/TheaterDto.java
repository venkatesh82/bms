package com.sample.bms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
public class TheaterDto {
    private String theaterName;
    private List<LocalTime> showTime;

    public TheaterDto() {
    }

    public TheaterDto(String theaterName, List<LocalTime> showTime) {
        this.theaterName = theaterName;
        this.showTime = showTime;
    }


}
