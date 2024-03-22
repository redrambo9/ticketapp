package com.coding.ticketapp.repository;

import com.coding.ticketapp.Model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public void removeUser(String email) {
        users.remove(email);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.get(email);
    }
}
