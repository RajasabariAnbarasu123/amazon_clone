package com.rajasabari.demo.controller;

import com.rajasabari.demo.model.OrderEntity;
import com.rajasabari.demo.model.User;
import com.rajasabari.demo.repository.OrderRepository;
import com.rajasabari.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    // Dummy payment simulation
    @PostMapping("/pay")
    public String makePayment(@RequestParam double amount) {

        User user = userRepository.findAll().get(0);

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setTotalAmount(amount);

        orderRepository.save(order);
        return "Payment successful. Order placed.";
    }
}
