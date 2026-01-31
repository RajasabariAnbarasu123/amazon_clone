package com.example.amazonclone.repository;

import com.example.amazonclone.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    //JPA NAMES
    List<Cart> findByUser_Id(Long userId);

    void deleteByUser_Id(Long userId);

    Optional<Cart> findByUser_IdAndProduct_Id(Long userId, Long productId);
}
