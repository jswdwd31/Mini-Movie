package com.example.minimovie.entity;

import com.example.minimovie.dto.request.TheaterRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Theater extends BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Long id;

    private String name;

    private String location;

    private String telephone;

    @Builder
    public Theater(String name, String location, String telephone) {
        this.name = name;
        this.location = location;
        this.telephone = telephone;
    }

    public void updateTheater(TheaterRequestDto updateTheater) {
        this.name = updateTheater.getName();
        this.location = updateTheater.getLocation();
        this.telephone = updateTheater.getTelephone();
    }
}
