package com.coding.ticketapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Seat {
    private String section;
    private int seatNumber;
}
