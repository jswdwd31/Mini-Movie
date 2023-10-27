package com.example.minimovie.controller;

import com.example.minimovie.dto.response.MovieResponseDto;
import com.example.minimovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movieList")
    public String movieList(Model model) {
        List<MovieResponseDto> movieList = movieService.readAllMovies();

        model.addAttribute("movieList", movieList);

        return "movie/movieList";
    }

    @GetMapping("/movieDetail/{movieId}")
    public String movieDetail(@PathVariable Long movieId, Model model) {
        MovieResponseDto movie = movieService.readMovieDetail(movieId);

        model.addAttribute("movie", movie);

        return "movie/movieDetail";
    }
}
