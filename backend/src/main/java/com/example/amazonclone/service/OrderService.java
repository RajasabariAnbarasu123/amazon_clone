package com.example.amazonclone.service;

import com.example.amazonclone.entity.Order;

import java.util.List;

public interface OrderService {

    // Place order after successful payment
    void placeOrder(Long userId, String paymentStatus);

    // Get all orders of a user
    List<Order> getOrdersByUser(Long userId);

    // Get all orders of a seller
    List<Order> getOrdersBySeller(Long sellerId);
}
