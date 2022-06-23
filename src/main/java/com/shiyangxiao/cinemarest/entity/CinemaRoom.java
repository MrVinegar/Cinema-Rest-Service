package com.shiyangxiao.cinemarest.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CinemaRoom {

    private final int totalRows;
    private final int totalColumns;
    private List<Seat> availableSeats;

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = new ArrayList<>();
        // generates seats
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                this.availableSeats.add(new Seat(i+1, j+1));
            }
        }
    }
}
