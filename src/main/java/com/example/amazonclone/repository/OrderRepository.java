package com.example.amazonclone.repository;

import com.example.amazonclone.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Get orders by user
    List<Order> findByUserId(Long userId);

    // Get orders by seller
    List<Order> findBySellerId(Long sellerId);
}
