package com.sample.bms.service;

import com.sample.bms.domain.Customer;
import com.sample.bms.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    public final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "customersById", key = "#id")
    public Customer findById(Long id){
        log.info("Searching for Customer with ID {} ...", id);
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
