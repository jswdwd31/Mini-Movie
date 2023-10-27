package com.example.minimovie.controller;

import com.example.minimovie.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;

    private final MovieService movieService;

    private final RateService rateService;

    private final ScreenService screenService;

    private final TheaterService theaterService;

    @GetMapping("/adminMain")
    public void adminMain() {

    }
}
