package com.shiyangxiao.cinemarest.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInfo {
    private int row;
    private int column;
    private int price;


}
