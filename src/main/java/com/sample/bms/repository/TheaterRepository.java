package com.sample.bms.repository;

import com.sample.bms.domain.Theater;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {

    @NotNull Optional<Theater> findById(@NotNull Long id);
}

