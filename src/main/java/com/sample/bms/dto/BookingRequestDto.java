package com.sample.bms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDto {

    @NotNull
    private Long customerId;

    @NotNull
    private Long showId;

    private List<Long> offerIds;

    @NotNull
    private List<Long> seatIds;

}
