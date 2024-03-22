package com.coding.ticketapp.controllers;

import com.coding.ticketapp.Constants;
import com.coding.ticketapp.Model.Seat;
import com.coding.ticketapp.Model.Ticket;
import com.coding.ticketapp.Model.TicketRequest;
import com.coding.ticketapp.Model.User;
import com.coding.ticketapp.repository.TicketRepository;
import com.coding.ticketapp.services.SeatService;
import com.coding.ticketapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketing")
class TicketController {
    @Autowired
    private UserService userService;

    @Autowired
    private SeatService seatService;

    @Autowired
    TicketRepository ticketRepository;

    @PostMapping(path = {"/purchase"}, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody TicketRequest request) {
        User user = request.getUser();
        if (ticketRepository.findByUser(user).size() > 0) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.addUser(user);
        Seat seat = seatService.assignSeat(user);
        Ticket ticket = new Ticket(request.getFrom(), request.getTo(), user, seat, Constants.ticketPrice);
        ticketRepository.addTicket(ticket);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }
}
