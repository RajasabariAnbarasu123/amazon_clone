package com.example.amazonclone.repository;

import com.example.amazonclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email 
    Optional<User> findByEmail(String email);

    // Check email already exists 
    boolean existsByEmail(String email);
}
