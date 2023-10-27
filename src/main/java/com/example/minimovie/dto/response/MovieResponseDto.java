package com.example.minimovie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {

    private Long movieId;

    private String title;

    private String summary;

    private int grade;

    private int runningTime;

    // 영화에 대한 총 평점
    private Double rateToTotal;

    // 영화에 대한 평론가 평점 (member-role = 2)
    private Double rateToCritic;

    // 영화에 대한 일반 관람객 평점 (member-role =1)
    private Double rateToGeneral;

}
