package com.example.minimovie.service;

import com.example.minimovie.dto.request.RateRequestDto;
import com.example.minimovie.dto.response.RateResponseDto;
import com.example.minimovie.entity.Member;
import com.example.minimovie.entity.Movie;
import com.example.minimovie.entity.Rate;
import com.example.minimovie.repository.MemberRepository;
import com.example.minimovie.repository.MovieRepository;
import com.example.minimovie.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;

    private final MemberRepository memberRepository;

    private final MovieRepository movieRepository;

    public void createRate(RateRequestDto createDto) {
        rateRepository.save(toEntity(createDto));
    }

    public RateResponseDto readRateDetail(Long rateId) {
        return toDto(rateRepository.findById(rateId).get());
    }

    public List<RateResponseDto> readRatesByMovie(Long movieId) {
        return rateRepository.findRatesByMovie_Id(movieId).stream().map(this::toDto).toList();
    }

    public List<RateResponseDto> readRatesByMember(Long memberId) {
        return rateRepository.findRatesByMember_Id(memberId).stream().map(this::toDto).toList();
    }

    public List<RateResponseDto> readAllRates() {
        return rateRepository.findAll().stream().map(this::toDto).toList();
    }

    public void updateRate(RateRequestDto updateDto) {
        Optional<Rate> rate = rateRepository.findById(updateDto.getRateId());
        isRate(rate);

        Optional<Member> member = memberRepository.findById(updateDto.getMemberId());
        isMember(member);

        Optional<Movie> movie = movieRepository.findById(updateDto.getMovieId());
        isMovie(movie);

        rate.get().updateRate(updateDto, member.get(), movie.get());

        rateRepository.save(rate.get());
    }

    public void deleteRate(Long rateId) {
        Optional<Rate> rate = rateRepository.findById(rateId);

        isRate(rate);

        rateRepository.delete(rate.get());
    }

    private void isMember(Optional<Member> member) {
        if (member.isEmpty()) {
            throw new RuntimeException();
        }
    }

    private void isMovie(Optional<Movie> movie) {
        if (movie.isEmpty()) {
            throw new RuntimeException();
        }
    }

    private void isRate(Optional<Rate> rate) {
        if (rate.isEmpty()) {
            throw new RuntimeException();
        }
    }

    private Rate toEntity(RateRequestDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getMemberId());
        isMember(member);

        Optional<Movie> movie = movieRepository.findById(dto.getMovieId());
        isMovie(movie);

        if (member.get().getRole() == 2) {
            return Rate.builder()
                    .member(member.get())
                    .movie(movie.get())
                    .star(dto.getStar())
                    .review(dto.getReview())
                    .build();
        } else {
            return Rate.builder()
                    .member(member.get())
                    .movie(movie.get())
                    .star(dto.getStar())
                    .build();
        }
    }

    private RateResponseDto toDto(Rate rate) {
        return RateResponseDto.builder()
                .rateId(rate.getId())
                .memberNickname(rate.getMember().getNickname())
                .memberRole(rate.getMember().getRole())
                .movieTitle(rate.getMovie().getTitle())
                .star(rate.getStar())
                .review(rate.getReview())
                .build();
    }

}
