package com.sample.bms.service;

import com.sample.bms.domain.Show;
import com.sample.bms.domain.Theater;
import com.sample.bms.dto.ShowDto;
import com.sample.bms.dto.TheaterDto;
import com.sample.bms.dto.TheaterShowsDto;
import com.sample.bms.repository.ShowRepository;
import com.sample.bms.repository.TheaterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TheaterService {

    private final ShowRepository showRepository;
    private final TheaterRepository theaterRepository;

    public TheaterService(ShowRepository showRepository, TheaterRepository theaterRepository) {
        this.showRepository = showRepository;
        this.theaterRepository = theaterRepository;
    }

    @Transactional(readOnly = true)
    public List<TheaterDto> getTheaters(String cityName, String movieTitle, LocalDate chosenDate) {
        List<Show> shows = showRepository.findShowsByCityNameAndMovieTitleAndDate(cityName, movieTitle, chosenDate);
        return convertToDtoList(shows);
    }


    public List<TheaterShowsDto> getTheatersAndShowsByOffer(String offerName, String cityName, LocalDate chosenDate) {
        List<Show> shows = showRepository.getShowsByOfferNameAndDate(offerName, cityName, chosenDate);
        return convertToTheaterShowsDtoList(shows);
    }

    @Transactional(readOnly = true)
    public Optional<Theater> getTheaterById(Long id) {
        return theaterRepository.findById(id);
    }

    private List<TheaterShowsDto> convertToTheaterShowsDtoList(List<Show> shows) {
        Map<String, List<Show>> theaterShowTimesMap = shows.stream()
                .collect(Collectors.groupingBy(show -> show.getTheater().getName()));

        return theaterShowTimesMap.entrySet().stream()
                .map(entry -> new TheaterShowsDto(entry.getKey(), convertToShowDtoList(entry.getValue())))
                .collect(Collectors.toList());
    }

    private List<ShowDto> convertToShowDtoList(List<Show> shows) {
        return shows.stream()
                .map(this::convertToShowDto)
                .collect(Collectors.toList());
    }

    private ShowDto convertToShowDto(Show show) {
        ShowDto showDto = new ShowDto();
        showDto.setMovieTitle(show.getMovie().getTitle());
        showDto.setShowTime(show.getShowTime());
        showDto.setShowDate(show.getDate());
        return showDto;
    }


    private List<TheaterDto> convertToDtoList(List<Show> shows) {
        Map<String, List<LocalTime>> theaterShowTimesMap = new HashMap<>();
        for (Show show : shows) {
            theaterShowTimesMap.computeIfAbsent(show.getTheater().getName(), k -> new ArrayList<>()).add(show.getShowTime());
        }

        List<TheaterDto> theaterDtoList = new ArrayList<>();
        for (Map.Entry<String, List<LocalTime>> entry : theaterShowTimesMap.entrySet()) {
            TheaterDto theaterDto = new TheaterDto(entry.getKey(), entry.getValue());
            theaterDtoList.add(theaterDto);
        }
        return theaterDtoList;
    }

}
