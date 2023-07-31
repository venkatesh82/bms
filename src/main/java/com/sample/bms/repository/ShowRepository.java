package com.sample.bms.repository;

import com.sample.bms.domain.Show;

import com.sample.bms.domain.Theater;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query("SELECT s FROM Show s " +
            "JOIN FETCH s.theater t " +
            "JOIN FETCH t.city c " +
            "WHERE t.city.name = :cityName " +
            "AND s.movie.title = :movieTitle " +
            "AND s.date = :chosenDate")
    List<Show> findShowsByCityNameAndMovieTitleAndDate(@Param("cityName") String cityName,
                                                       @Param("movieTitle") String movieTitle,
                                                       @Param("chosenDate") LocalDate chosenDate);


    @Query("SELECT s FROM Show s " +
            "JOIN FETCH s.theater t " +
            "JOIN FETCH t.city c " +
            "WHERE EXISTS (SELECT o FROM Offer o WHERE o.offerName = :offerName AND o MEMBER OF t.offers) " +
            "AND t.city.name = :cityName  " +
            "AND s.date = :chosenDate")
    List<Show> getShowsByOfferNameAndDate(@Param("offerName") String offerName, @Param("cityName") String cityName, @Param("chosenDate") LocalDate chosenDate);


    @NotNull Optional<Show> findById(@NotNull Long id);

    List<Show> findByTheaterAndDate(Theater theater, LocalDate showDate);

    @Query("SELECT DISTINCT s.movie.title FROM Show s " +
            "JOIN s.theater t " +
            "JOIN t.city c " +
            "WHERE s.date = :showDate")
    List<String> findDistinctShowNamesByDate(@Param("showDate") LocalDate showDate);




}

