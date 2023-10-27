package com.example.minimovie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreenResponseDto {

    private Long screenId;

    private String movieTitle;

    private String movieSummary;

    private String theaterName;

    private String theaterLocation;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
