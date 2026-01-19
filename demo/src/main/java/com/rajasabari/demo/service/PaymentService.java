package com.rajasabari.demo.service;

import com.rajasabari.demo.entity.Payment;

public interface PaymentService {

    Payment makePayment(Long userId, double amount);
}
