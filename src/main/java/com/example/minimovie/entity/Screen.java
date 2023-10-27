package com.example.minimovie.entity;

import com.example.minimovie.dto.request.ScreenRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Screen extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    private LocalDateTime startDate;

    @Builder
    public Screen(Movie movie, Theater theater, LocalDateTime startDate) {
        this.movie = movie;
        this.theater = theater;
        this.startDate = startDate;
    }

    public void updateScreen(ScreenRequestDto updateScreen, Movie updateMovie, Theater updateTheater) {
        this.startDate = updateScreen.getStartDate();
        this.movie = updateMovie;
        this.theater = updateTheater;
    }

}
