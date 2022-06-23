package com.shiyangxiao.cinemarest.entity;

import com.shiyangxiao.cinemarest.entity.CinemaRoom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinemaConfig {

    @Bean
    public CinemaRoom cinemaRoom(@Value("${cinema.room.rows}") int rows, @Value("${cinema.room.columns}") int columns) {
        return new CinemaRoom(rows, columns);
    }

    @Bean
    public Statistics statistics(@Value("${cinema.room.rows}") int rows, @Value("${cinema.room.columns}") int columns) {
        return new Statistics(rows, columns);
    }

}