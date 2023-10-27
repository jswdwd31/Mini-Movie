package com.example.minimovie.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequestDto {

    private Long movieId;

    private String title;

    private String summary;

    private int grade;

    private int runningTime;

}
