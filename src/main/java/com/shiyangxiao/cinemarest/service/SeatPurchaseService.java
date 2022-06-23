package com.shiyangxiao.cinemarest.service;

import com.shiyangxiao.cinemarest.DTO.OrderInfo;
import com.shiyangxiao.cinemarest.DTO.ReturnInfo;
import com.shiyangxiao.cinemarest.DTO.TicketInfo;
import com.shiyangxiao.cinemarest.Exception.SeatNotFoundException;
import com.shiyangxiao.cinemarest.entity.CinemaRoom;
import com.shiyangxiao.cinemarest.entity.Seat;
import com.shiyangxiao.cinemarest.entity.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SeatPurchaseService {

    private final CinemaRoom cinemaRoom;
    private final Statistics statistics;
    private final Map<String, OrderInfo> orders;

    @Autowired
    public SeatPurchaseService(CinemaRoom cinemaRoom, Statistics statistics) {
        this.cinemaRoom = cinemaRoom;
        this.statistics = statistics;
        this.orders = new ConcurrentHashMap<>();
    }

    public OrderInfo getTicket(Seat seat) throws Exception {
        OrderInfo order = null;
        Seat selectedSeat = cinemaRoom.getAvailableSeats().stream()
                .filter(seat1 -> seat1.getRow() == seat.getRow() && seat1.getColumn() == seat.getColumn())
                .findAny()
                .orElseThrow(() -> new SeatNotFoundException("The number of a row or a column is out of bounds!"));

        if (selectedSeat.isAvailable()) {
            selectedSeat.setAvailable(false);
            // set token to the selected seat
            order = new OrderInfo(UUID.randomUUID().toString(),
                    new TicketInfo(selectedSeat.getRow(), selectedSeat.getColumn(), selectedSeat.getPrice()));
            // add the order to map
            orders.put(order.getToken(), order);

            // edit stat
            statistics.modifyStat(true, selectedSeat.getPrice(), 1);
        } else {
            throw new Exception("The ticket has been already purchased!");
        }
        return order;
    }

    public ReturnInfo refundTicket(String token) throws Exception {
        if (orders.containsKey(token)) {
            TicketInfo ticketInfo = orders.get(token).getTicket();
            cinemaRoom.getAvailableSeats().stream()
                    .filter(seat1 -> seat1.getRow() == ticketInfo.getRow() && seat1.getColumn() == ticketInfo.getColumn())
                            .findAny()
                                    .ifPresent(seat -> seat.setAvailable(true));
            orders.remove(token);

            // edit stat
            statistics.modifyStat(false, ticketInfo.getPrice(), 1);
            return new ReturnInfo(ticketInfo);
        } else {
            throw new RuntimeException("Wrong token!");
        }
    }


}
