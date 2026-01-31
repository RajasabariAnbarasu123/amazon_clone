package com.example.amazonclone.service;

import com.example.amazonclone.entity.User;

public interface UserService {

    // Register new user / seller
    User registerUser(User user);

    // Login user
    String loginUser(String email, String password);

    // Get user by ID
    User getUserById(Long userId);

    // Get user by email 
    User getUserByEmail(String email);
}
