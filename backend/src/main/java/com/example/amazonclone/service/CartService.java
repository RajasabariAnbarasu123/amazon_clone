package com.example.amazonclone.service;

import com.example.amazonclone.entity.Cart;

import java.util.List;

public interface CartService {

    // Add product to cart
    Cart addToCart(Long userId, Long productId, int quantity);

    // Get all cart items for a user
    List<Cart> getUserCart(Long userId);

    // Remove single cart item
    void removeFromCart(Long cartId);

    // Clear cart after successful order
    void clearCartByUser(Long userId);
}
