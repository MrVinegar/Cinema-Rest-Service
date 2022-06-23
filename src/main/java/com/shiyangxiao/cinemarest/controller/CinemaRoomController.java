package com.shiyangxiao.cinemarest.controller;

import com.shiyangxiao.cinemarest.DTO.RefundRequest;
import com.shiyangxiao.cinemarest.Exception.InvalidPasswordException;
import com.shiyangxiao.cinemarest.entity.Seat;
import com.shiyangxiao.cinemarest.service.RoomInfoService;
import com.shiyangxiao.cinemarest.service.SeatPurchaseService;
import com.shiyangxiao.cinemarest.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaRoomController {

    private final RoomInfoService roomInfoService;
    private final SeatPurchaseService seatPurchaseService;
    private final StatService statService;

    @Autowired
    public CinemaRoomController(RoomInfoService roomInfoService, SeatPurchaseService seatPurchaseService, StatService statService) {
        this.roomInfoService = roomInfoService;
        this.seatPurchaseService = seatPurchaseService;
        this.statService = statService;
    }

    @GetMapping("/seats")
    public ResponseEntity<?> getInfo() {
        return ResponseEntity.ok(roomInfoService.getCinemaRoom());
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat seat) {
        try {
            return ResponseEntity.ok(seatPurchaseService.getTicket(seat));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody RefundRequest refundRequest) {
        try {
            return ResponseEntity.ok(seatPurchaseService.refundTicket(refundRequest.getToken()));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(required = false) String password) throws Exception {
        try {
            return ResponseEntity.ok(statService.getStatistics(password));
        } catch (InvalidPasswordException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
