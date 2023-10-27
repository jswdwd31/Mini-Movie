package com.example.minimovie.entity;

import com.example.minimovie.dto.request.RateRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Rate extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private int star;

    @Column(columnDefinition = "TEXT")
    private String review;

    @Builder
    public Rate(Member member, Movie movie, int star, String review) {
        this.member = member;
        this.movie = movie;
        this.star = star;
        this.review = review;
    }

    public void updateRate(RateRequestDto updateRate, Member updateMember, Movie updateMovie) {
        this.review = updateRate.getReview();
        this.star = updateRate.getStar();
        this.member = updateMember;
        this.movie = updateMovie;
    }
}
