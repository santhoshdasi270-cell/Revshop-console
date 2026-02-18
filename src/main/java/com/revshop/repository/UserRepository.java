package com.revshop.repository;

import com.revshop.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    // Key = email, Value = User
    private Map<String, User> users = new HashMap<>();

    // Save user
    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    // Find user by email
    public User findByEmail(String email) {
        return users.get(email);
    }

    // Check if email exists
    public boolean existsByEmail(String email) {
        return users.containsKey(email);
    }
}
