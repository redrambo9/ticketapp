package com.coding.ticketapp.repository;

import com.coding.ticketapp.Model.Seat;
import com.coding.ticketapp.Model.User;

import java.util.List;
import java.util.Map;


public interface SeatRepository {
    void addSeatForUser(User user, Seat seat);

    void modifySeat(String email, Seat seat);

    void removeSeat(String email);
    Map<String, Seat> getSeatsInSection(String section);
}
