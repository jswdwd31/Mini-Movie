package com.example.minimovie.service;

import com.example.minimovie.dto.request.MovieRequestDto;
import com.example.minimovie.dto.response.MovieResponseDto;
import com.example.minimovie.entity.Movie;
import com.example.minimovie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public void createMovie(MovieRequestDto createDto) {
        movieRepository.save(toEntity(createDto));
    }

    public MovieResponseDto readMovieDetail(Long movieId) {
        return toDto(movieRepository.findById(movieId).get());
    }

    public List<MovieResponseDto> readAllMovies() {
        return movieRepository.findAll().stream().map(this::toDto).toList();
    }

    public void updateMovie(MovieRequestDto updateDto) {
        Optional<Movie> movie = movieRepository.findById(updateDto.getMovieId());

        isMovie(movie);

        movie.get().updateMovie(updateDto);

        movieRepository.save(movie.get());
    }

    public void deleteMovie(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);

        isMovie(movie);

        movieRepository.delete(movie.get());
    }

    private void isMovie(Optional<Movie> movie) {
        if (movie.isEmpty()) {
            throw new RuntimeException();
        }
    }

    private Movie toEntity(MovieRequestDto dto) {
        return Movie.builder()
                .title(dto.getTitle())
                .summary(dto.getSummary())
                .grade(dto.getGrade())
                .runningTime(dto.getRunningTime())
                .build();
    }

    private MovieResponseDto toDto(Movie movie) {
        return MovieResponseDto.builder()
                .movieId(movie.getId())
                .title(movie.getTitle())
                .summary(movie.getSummary())
                .grade(movie.getGrade())
                .runningTime(movie.getRunningTime())
                .rateToTotal(movieRepository.findTotalAverageStarByMovieId(movie.getId()))
                .rateToCritic(movieRepository.findCriticAverageStarByMovieId(movie.getId()))
                .rateToGeneral(movieRepository.findGeneralAverageStarByMovieId(movie.getId()))
                .build();
    }

}
