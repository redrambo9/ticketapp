package com.coding.ticketapp.Model;

import lombok.Data;

@Data
public class TicketRequest {
    private String from;
    private String to;
    private User user;
}
