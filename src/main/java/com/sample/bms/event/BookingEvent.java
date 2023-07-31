package com.sample.bms.event;


import com.sample.bms.domain.Booking;
import com.sample.bms.domain.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookingEvent {
    private Booking booking;
    private List<Seat> seats;
}
