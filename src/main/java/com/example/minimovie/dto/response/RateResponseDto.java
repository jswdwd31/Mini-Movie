package com.example.minimovie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateResponseDto {

    private Long rateId;

    private String memberNickname;

    private int memberRole;

    private String movieTitle;

    private int star;

    private String review;

}
