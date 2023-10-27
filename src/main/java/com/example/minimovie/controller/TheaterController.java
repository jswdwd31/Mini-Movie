package com.example.minimovie.controller;

import com.example.minimovie.dto.response.MovieResponseDto;
import com.example.minimovie.dto.response.TheaterResponseDto;
import com.example.minimovie.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/theater")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    @GetMapping("/theaterList")
    public String movieList(Model model) {
        List<TheaterResponseDto> theaterList = theaterService.readAllTheaters();

        model.addAttribute("theaterList", theaterList);

        return "theater/theaterList";
    }

    @GetMapping("/theaterDetail/{theaterId}")
    public String movieDetail(@PathVariable Long theaterId, Model model) {
        TheaterResponseDto theater = theaterService.readTheaterDetail(theaterId);

        model.addAttribute("theater", theater);

        return "theater/theaterDetail";
    }
}
