package com.sample.bms.domain;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String bookingNbr;

    private LocalDateTime bookingTime;

    private BigDecimal totalPrice;

    private String status;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("show", show)
                .append("customer", customer.getName())
                .append("bookingNbr", bookingNbr)
                .append("bookingTime", bookingTime)
                .toString();
    }
}
