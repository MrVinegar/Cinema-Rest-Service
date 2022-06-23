package com.shiyangxiao.cinemarest.service;

import com.shiyangxiao.cinemarest.entity.CinemaRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomInfoService {
    private final CinemaRoom cinemaRoom;

    @Autowired
    public RoomInfoService(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

}
