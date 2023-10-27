package com.example.minimovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MiniMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniMovieApplication.class, args);
    }

}
