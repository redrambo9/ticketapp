package com.coding.ticketapp.repository;

import com.coding.ticketapp.Model.User;

public interface UserRepository {
    void addUser(User user);

    void removeUser(String email);

    User getUserByEmail(String email);
}
