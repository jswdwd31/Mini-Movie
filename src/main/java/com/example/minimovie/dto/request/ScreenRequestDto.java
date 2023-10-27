package com.example.minimovie.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScreenRequestDto {

    private Long screenId;

    private LocalDateTime startDate;

    private Long movieId;

    private Long theaterId;

}
