package com.example.amazonclone.controller;

import com.example.amazonclone.entity.Order;
import com.example.amazonclone.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // PLACE ORDER
    @PostMapping("/place/{userId}")
    public ResponseEntity<String> placeOrder(
            @PathVariable Long userId,
            @RequestParam String paymentStatus
    ) {
        try {
            orderService.placeOrder(userId, paymentStatus);
            return ResponseEntity.ok("Order placed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // VIEW USER ORDERS
    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }

    // VIEW SELLER ORDERS
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Order>> getSellerOrders(@PathVariable Long sellerId) {
        return ResponseEntity.ok(orderService.getOrdersBySeller(sellerId));
    }
}
