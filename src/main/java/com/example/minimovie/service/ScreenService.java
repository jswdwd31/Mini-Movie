package com.example.minimovie.service;

import com.example.minimovie.dto.request.ScreenRequestDto;
import com.example.minimovie.dto.response.ScreenResponseDto;
import com.example.minimovie.entity.Movie;
import com.example.minimovie.entity.Screen;
import com.example.minimovie.entity.Theater;
import com.example.minimovie.repository.MovieRepository;
import com.example.minimovie.repository.ScreenRepository;
import com.example.minimovie.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScreenService {

    private final ScreenRepository screenRepository;

    private final MovieRepository movieRepository;

    private final TheaterRepository theaterRepository;

    public void createScreen(ScreenRequestDto createDto) {
        screenRepository.save(toEntity(createDto));
    }

    public ScreenResponseDto readScreenDetail(Long screenId) {
        return toDto(screenRepository.findById(screenId).get());
    }

    public List<ScreenResponseDto> readScreensByMovie(Long movieId) {
        return screenRepository.findScreensByMovie_Id(movieId).stream().map(this::toDto).toList();
    }

    public List<ScreenResponseDto> readScreensByTheater(Long theaterId) {
        return screenRepository.findScreensByTheater_Id(theaterId).stream().map(this::toDto).toList();
    }

    public List<ScreenResponseDto> readAllScreens() {
        return screenRepository.findAll().stream().map(this::toDto).toList();
    }

    public void updateScreen(ScreenRequestDto updateDto) {
        Optional<Screen> screen = screenRepository.findById(updateDto.getScreenId());
        isScreen(screen);

        Optional<Movie> movie = movieRepository.findById(updateDto.getMovieId());
        isMovie(movie);

        Optional<Theater> theater = theaterRepository.findById(updateDto.getTheaterId());
        isTheater(theater);

        screen.get().updateScreen(updateDto, movie.get(), theater.get());

        screenRepository.save(screen.get());
    }

    public void deleteScreen(Long screenId) {
        Optional<Screen> screen = screenRepository.findById(screenId);

        isScreen(screen);

        screenRepository.delete(screen.get());
    }

    private void isScreen(Optional<Screen> screen) {
        if (screen.isEmpty()) {
            throw new RuntimeException();
        }
    }

    private void isMovie(Optional<Movie> movie) {
        if (movie.isEmpty()) {
            throw new RuntimeException();
        }
    }

    private void isTheater(Optional<Theater> theater) {
        if (theater.isEmpty()) {
            throw new RuntimeException();
        }
    }

    private Screen toEntity(ScreenRequestDto dto) {
        Optional<Movie> movie = movieRepository.findById(dto.getMovieId());
        isMovie(movie);

        Optional<Theater> theater = theaterRepository.findById(dto.getTheaterId());
        isTheater(theater);

        return Screen.builder()
                .movie(movie.get())
                .theater(theater.get())
                .startDate(dto.getStartDate())
                .build();
    }

    private ScreenResponseDto toDto(Screen screen) {
        return ScreenResponseDto.builder()
                .screenId(screen.getId())
                .movieTitle(screen.getMovie().getTitle())
                .movieSummary(screen.getMovie().getSummary())
                .theaterName(screen.getTheater().getName())
                .theaterLocation(screen.getTheater().getLocation())
                .startDate(screen.getStartDate())
                .endDate(screen.getStartDate().plusMinutes(screen.getMovie().getRunningTime()))
                .build();
    }

}
