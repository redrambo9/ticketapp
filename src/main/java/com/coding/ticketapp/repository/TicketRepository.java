package com.coding.ticketapp.repository;

import com.coding.ticketapp.Model.Ticket;
import com.coding.ticketapp.Model.User;

import java.util.List;

public interface TicketRepository {
    List<Ticket> findByUser(User user);

    void addTicket(Ticket ticket);
}
