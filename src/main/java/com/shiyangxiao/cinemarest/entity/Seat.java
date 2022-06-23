package com.shiyangxiao.cinemarest.entity;

import lombok.Data;
@Data
public class Seat {
    private final int row;
    private final int column;
    private final int price;
    private boolean isAvailable;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.isAvailable = true;
        this.price = row <= 4 ? 10 : 8;
    }
}
