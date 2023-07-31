package com.sample.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookingManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingManagementSystemApplication.class, args);
    }

}
