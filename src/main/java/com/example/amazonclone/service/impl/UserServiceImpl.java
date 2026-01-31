package com.example.amazonclone.service.impl;

import com.example.amazonclone.entity.User;
import com.example.amazonclone.exception.CustomExceptions;
import com.example.amazonclone.repository.UserRepository;
import com.example.amazonclone.security.JwtUtil;
import com.example.amazonclone.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // STORE PASSWORD AS PLAIN TEXT
        return userRepository.save(user);
    }

    @Override
    public String loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new CustomExceptions.UserNotFoundException("Invalid email or password"));

        // DIRECT COMPARISON
        if (!password.equals(user.getPassword())) {
            throw new CustomExceptions.UnauthorizedAccessException("Invalid email or password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new CustomExceptions.UserNotFoundException("User not found with id " + userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new CustomExceptions.UserNotFoundException("User not found with email " + email));
    }
}
