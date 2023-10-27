package com.example.minimovie.entity;

import com.example.minimovie.dto.request.MovieRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String summary;

    private int grade;

    private int runningTime;

    @Builder
    public Movie(String title, String summary, int grade, int runningTime) {
        this.title = title;
        this.summary = summary;
        this.grade = grade;
        this.runningTime = runningTime;
    }

    public void updateMovie(MovieRequestDto updateMovie) {
        this.title = updateMovie.getTitle();
        this.summary = updateMovie.getSummary();
        this.grade = updateMovie.getGrade();
        this.runningTime = updateMovie.getRunningTime();
    }

}
