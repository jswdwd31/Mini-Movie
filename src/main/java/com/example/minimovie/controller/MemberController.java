package com.example.minimovie.controller;

import com.example.minimovie.dto.request.MemberRequestDto;
import com.example.minimovie.dto.response.MemberResponseDto;
import com.example.minimovie.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public void join() {

    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberRequestDto memberRequestDto, RedirectAttributes redirectAttributes) {

        memberService.createMember(memberRequestDto);

        String memberIdentity = memberRequestDto.getIdentity();

        redirectAttributes.addFlashAttribute("memberIdentity", memberIdentity);

        return "redirect:/home";
    }

    @GetMapping("/login")
    public void login() {

    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberRequestDto memberRequestDto, HttpSession session) {

        MemberResponseDto loginMember = memberService.loginMember(memberRequestDto.getIdentity(), memberRequestDto.getPassword());

        session.setAttribute("loginMember", loginMember);

        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginMember");

        return "redirect:/home"; // 로그아웃 후 홈 페이지로 리다이렉트
    }

}
