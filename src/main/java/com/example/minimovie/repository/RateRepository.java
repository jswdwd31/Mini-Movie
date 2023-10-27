package com.example.minimovie.repository;

import com.example.minimovie.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    List<Rate> findRatesByMovie_Id(Long movieId);

    List<Rate> findRatesByMember_Id(Long memberId);
}
