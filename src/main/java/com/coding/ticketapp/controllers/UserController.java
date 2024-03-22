package com.coding.ticketapp.controllers;

import com.coding.ticketapp.Model.Seat;
import com.coding.ticketapp.Model.Ticket;
import com.coding.ticketapp.Model.User;
import com.coding.ticketapp.repository.TicketRepository;
import com.coding.ticketapp.services.SeatService;
import com.coding.ticketapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    SeatService seatService;

    private final TicketRepository ticketRepository;

    public UserController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/receipt/{email}")
    public ResponseEntity<List<Ticket>> getReceipt(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Ticket> tickets = ticketRepository.findByUser(user);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/section/{section}")
    public ResponseEntity<Map<User, Seat>> getUsersBySection(@PathVariable String section) {
        Map<User, Seat> usersBySection = seatService.getUsersBySection(section);
        return new ResponseEntity<>(usersBySection, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{email}")
    public ResponseEntity<String> removeUser(@PathVariable String email) {
        userService.removeUser(email);
        seatService.removeSeat(email);
        return new ResponseEntity<>("User removed successfully", HttpStatus.OK);
    }

    @PutMapping("/modify/{email}")
    public ResponseEntity<String> modifyUserSeat(@PathVariable String email, @RequestBody Seat seat) {
        seatService.modifySeat(email, seat);
        return new ResponseEntity<>("User seat modified successfully", HttpStatus.OK);
    }
}
