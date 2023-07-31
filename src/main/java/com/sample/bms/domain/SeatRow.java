package com.sample.bms.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class SeatRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    private String rowNumber;

    public SeatRow(Theater theater, String rowNumber) {
        this.theater = theater;
        this.rowNumber = rowNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("rowNumber", rowNumber)
                .toString();
    }

// Constructors, getters, setters, etc.
}

