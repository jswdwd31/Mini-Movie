package com.example.minimovie.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateRequestDto {

    private Long rateId;

    private int star;

    private String review;

    private Long memberId;

    private Long movieId;

}
