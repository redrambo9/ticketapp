package com.coding.ticketapp.repository;

import com.coding.ticketapp.Model.Seat;
import com.coding.ticketapp.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SeatRepositoryImpl implements SeatRepository {
    private final Map<String, Seat> seats = new HashMap<>();

    @Override
    public void addSeatForUser(User user, Seat seat) {
        seats.put(user.getEmail(), seat);
    }

    @Override
    public void modifySeat(String email, Seat seat) {
        seats.put(email, seat);
    }

    @Override
    public void removeSeat(String email) {
        seats.remove(email);
    }

    @Override
    public Map<String, Seat> getSeatsInSection(String section) {
        Map<String, Seat> seatsInSectionB = new HashMap<>();
        for (Map.Entry<String, Seat> entry : seats.entrySet()) {
            String userEmail = entry.getKey();
            Seat seat = entry.getValue();
            if (section.equals(seat.getSection())) {
                seatsInSectionB.put(userEmail, seat);
            }
        }
        return seatsInSectionB;
    }
}
