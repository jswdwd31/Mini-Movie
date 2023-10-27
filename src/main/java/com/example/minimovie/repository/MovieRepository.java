package com.example.minimovie.repository;

import com.example.minimovie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT AVG(r.star) " +
            "FROM Movie m " +
            "LEFT JOIN FETCH Rate r ON m.id = r.movie.id " +
            "WHERE m.id = :movieId")
    Double findTotalAverageStarByMovieId(Long movieId);

    @Query("SELECT AVG(r.star) " +
            "FROM Movie m " +
            "LEFT JOIN FETCH Rate r ON m.id = r.movie.id " +
            "WHERE m.id = :movieId AND r.member.role = 2")
    Double findCriticAverageStarByMovieId(Long movieId);

    @Query("SELECT AVG(r.star) " +
            "FROM Movie m " +
            "LEFT JOIN FETCH Rate r ON m.id = r.movie.id " +
            "WHERE m.id = :movieId AND r.member.role = 1")
    Double findGeneralAverageStarByMovieId(Long movieId);
}
