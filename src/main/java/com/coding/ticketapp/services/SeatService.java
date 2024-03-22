package com.coding.ticketapp.services;

import com.coding.ticketapp.Model.Seat;
import com.coding.ticketapp.Model.User;
import com.coding.ticketapp.repository.SeatRepository;
import com.coding.ticketapp.repository.SeatRepositoryImpl;
import com.coding.ticketapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SeatService {
    private final SeatRepository seatRepository;

    private final UserRepository userRepository;
    private static int seatNumber = 1;

    public SeatService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.seatRepository = new SeatRepositoryImpl();
    }

    public Seat assignSeat(User user) {
        // Randomly assign section
        String section = Math.random() < 0.5 ? "A" : "B";
        Seat seat = new Seat(section, seatNumber++);
        seatRepository.addSeatForUser(user, seat);
        return seat;
    }

    public void modifySeat(String email, Seat seat) {
        seatRepository.modifySeat(email, seat);
    }

    public Map<User, Seat> getUsersBySection(String section) {
        Map<User, Seat> usersAndSeats = new HashMap<>();

        // Retrieve seats allocated for the requested section from the seat repository
        Map<String, Seat> seatsInSection = seatRepository.getSeatsInSection(section);
        for (Map.Entry<String, Seat> entry : seatsInSection.entrySet()) {
            String userEmail = entry.getKey();
            Seat seat = entry.getValue();

            // Retrieve the user from the users map using the email
            User user = userRepository.getUserByEmail(userEmail);

            // If user is found, add the user and seat to the result map
            if (user != null) {
                usersAndSeats.put(user, seat);
            }
        }
        return usersAndSeats;
    }

    public void removeSeat(String email) {
        seatRepository.removeSeat(email);
    }
}
