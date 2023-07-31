package com.sample.bms.service;

import com.sample.bms.domain.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class PaymentService {
    public boolean processPayment(Booking booking, BigDecimal totalPrice) {
        log.info("Processing Payment");
        // Added to test concurrency
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
