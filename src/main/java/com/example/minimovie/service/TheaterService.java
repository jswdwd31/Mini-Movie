package com.example.minimovie.service;

import com.example.minimovie.dto.request.TheaterRequestDto;
import com.example.minimovie.dto.response.TheaterResponseDto;
import com.example.minimovie.entity.Theater;
import com.example.minimovie.repository.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheaterService {

    private final TheaterRepository theaterRepository;

    public void createTheater(TheaterRequestDto createDto) {
        theaterRepository.save(toEntity(createDto));
    }

    public TheaterResponseDto readTheaterDetail(Long theaterId) {
        return toDto(theaterRepository.findById(theaterId).get());
    }

    public List<TheaterResponseDto> readAllTheaters() {
        return theaterRepository.findAll().stream().map(this::toDto).toList();
    }

    public void updateTheater(TheaterRequestDto updateDto) {
        Optional<Theater> theater = theaterRepository.findById(updateDto.getTheaterId());

        isTheater(theater);

        theater.get().updateTheater(updateDto);

        theaterRepository.save(theater.get());
    }

    public void deleteTheater(Long theaterId) {
        Optional<Theater> theater = theaterRepository.findById(theaterId);

        isTheater(theater);

        theaterRepository.delete(theater.get());
    }

    private void isTheater(Optional<Theater> theater) {
        if (theater.isEmpty()) {
            throw new RuntimeException();
        }
    }

    private Theater toEntity(TheaterRequestDto dto) {
        return Theater.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .telephone(dto.getTelephone())
                .build();
    }

    private TheaterResponseDto toDto(Theater theater) {
        return TheaterResponseDto.builder()
                .theaterId(theater.getId())
                .name(theater.getName())
                .location(theater.getLocation())
                .telephone(theater.getTelephone())
                .build();
    }
}
