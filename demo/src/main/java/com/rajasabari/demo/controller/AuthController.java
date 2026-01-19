package com.rajasabari.demo.controller;

import com.rajasabari.demo.dto.LoginRequestDto;
import com.rajasabari.demo.dto.SignupRequestDto;
import com.rajasabari.demo.model.User;
import com.rajasabari.demo.repository.UserRepository;
import com.rajasabari.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            return "Email already registered";
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // plaintext (can add BCrypt later)
        user.setRole("USER");

        userRepository.save(user);
        return "Signup successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
