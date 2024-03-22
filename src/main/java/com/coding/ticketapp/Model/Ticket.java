package com.coding.ticketapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
    private String from;
    private String to;
    private User user;
    private Seat seat;
    private double pricePaid;
}
