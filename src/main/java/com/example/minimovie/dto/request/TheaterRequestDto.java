package com.example.minimovie.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterRequestDto {

    private Long theaterId;

    private String name;

    private String location;

    private String telephone;
}
