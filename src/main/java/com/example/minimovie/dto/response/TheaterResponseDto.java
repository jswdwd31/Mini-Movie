package com.example.minimovie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterResponseDto {

    private Long theaterId;

    private String name;

    private String location;

    private String telephone;


}
