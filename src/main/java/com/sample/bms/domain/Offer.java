package com.sample.bms.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OfferType offerType;

    private String offerName;

    private String description;

    private double discountAmount;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType; // Indicates whether discountAmount is a percentage or a flat value

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("offerType", offerType)
                .append("offerName", offerName)
                .append("description", description)
                .append("discountAmount", discountAmount)
                .append("discountType", discountType)
                .append("startDate", startDate)
                .append("endDate", endDate)
                .append("theater", theater)
                .toString();
    }

    // Constructors, getters, setters, etc.
}

