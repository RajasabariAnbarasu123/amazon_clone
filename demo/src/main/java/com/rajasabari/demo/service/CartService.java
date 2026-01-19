package com.rajasabari.demo.service;

import com.rajasabari.demo.entity.Cart;

public interface CartService {

    Cart addToCart(Long userId, Long productId, int quantity);

    Cart getCartByUserId(Long userId);

    void clearCart(Long userId);
}
