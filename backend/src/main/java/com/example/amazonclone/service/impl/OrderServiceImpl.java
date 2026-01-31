package com.example.amazonclone.service.impl;

import com.example.amazonclone.entity.Cart;
import com.example.amazonclone.entity.Order;
import com.example.amazonclone.exception.CustomExceptions;
import com.example.amazonclone.repository.CartRepository;
import com.example.amazonclone.repository.OrderRepository;
import com.example.amazonclone.service.OrderService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            CartRepository cartRepository
    ) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public void placeOrder(Long userId, String paymentStatus) {

        // Accept both "SUCCESS" and "PAID" statuses
        if (!"SUCCESS".equalsIgnoreCase(paymentStatus) && !"PAID".equalsIgnoreCase(paymentStatus)) {
            throw new CustomExceptions.PaymentFailedException("Payment not successful");
        }

        // FIXED
        List<Cart> cartItems = cartRepository.findByUser_Id(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        for (Cart cart : cartItems) {
            Order order = new Order();
            order.setUserId(userId);
            order.setSellerId(cart.getProduct().getSeller().getId());
            order.setProductId(cart.getProduct().getId());
            order.setQuantity(cart.getQuantity());
            order.setAmount(cart.getProduct().getPrice() * cart.getQuantity());
            // Store the original payment status
            order.setPaymentStatus(paymentStatus.toUpperCase());

            orderRepository.save(order);
        }

        // CLEAR CART
        cartRepository.deleteByUser_Id(userId);
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getOrdersBySeller(Long sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }
}
