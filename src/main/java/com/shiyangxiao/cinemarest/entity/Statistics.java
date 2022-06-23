package com.shiyangxiao.cinemarest.entity;

import lombok.Data;

@Data
public class Statistics {

    private int currentIncome;
    private int numberOfAvailableSeats;
    private int numberOfPurchasedTickets;

    public Statistics(int row, int col) {
        this.currentIncome = 0;
        this.numberOfAvailableSeats = row * col;
        this.numberOfPurchasedTickets = 0;
    }

    public void modifyStat(boolean purchase, int totalPrice, int numOfTicket) {
        if (purchase) {
            this.currentIncome += totalPrice;
            this.numberOfAvailableSeats -= numOfTicket;
            this.numberOfPurchasedTickets += numOfTicket;
        } else {
            this.currentIncome -= totalPrice;
            this.numberOfAvailableSeats += numOfTicket;
            this.numberOfPurchasedTickets -= numOfTicket;
        }
    }
}
