package com.coding.ticketapp.services;

import com.coding.ticketapp.Model.User;
import com.coding.ticketapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public void removeUser(String email) {
        userRepository.removeUser(email);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}

