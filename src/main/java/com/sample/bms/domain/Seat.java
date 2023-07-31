package com.sample.bms.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @ManyToOne
    @JoinColumn(name = "seat_row_id")
    private SeatRow seatRow;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    private String seatNumber;

    private BigDecimal price;

    private Boolean booked;

    @Column(name = "locked_at")
    private LocalDateTime lockedAt;

    @Version
    private Long version;

    public Seat(Theater theater, SeatRow seatRow, String seatNumber, BigDecimal price, Boolean booked, Long version) {
        this.theater = theater;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.price = price;
        this.booked = booked; // Newly added to remove synchronized at booking method level
        this.version = version; // Newly added to remove synchronized at booking method level
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("theater", theater)
                .append("seatRow", seatRow)
                .append("show", show)
                .append("price", price)
                .append("seatNumber", seatNumber)
                .toString();
    }

    // Constructors, getters, setters, etc.
}
