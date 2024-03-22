package com.coding.ticketapp.repository;

import com.coding.ticketapp.Model.Ticket;
import com.coding.ticketapp.Model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TicketRepositoryImpl implements TicketRepository {

    private final List<Ticket> tickets = new ArrayList<>();

    @Override
    public List<Ticket> findByUser(User user) {
        return tickets.stream()
                .filter(ticket -> ticket.getUser().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

}
