package com.example.amazonclone.service.impl;

import com.example.amazonclone.entity.Cart;
import com.example.amazonclone.entity.Product;
import com.example.amazonclone.entity.User;
import com.example.amazonclone.exception.CustomExceptions;
import com.example.amazonclone.repository.CartRepository;
import com.example.amazonclone.repository.ProductRepository;
import com.example.amazonclone.repository.UserRepository;
import com.example.amazonclone.service.CartService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(
            CartRepository cartRepository,
            UserRepository userRepository,
            ProductRepository productRepository
    ) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart addToCart(Long userId, Long productId, int quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new CustomExceptions.UserNotFoundException("User not found: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new CustomExceptions.ProductNotFoundException("Product not found: " + productId));

        Cart cart = cartRepository
                .findByUser_IdAndProduct_Id(userId, productId)
                .orElse(new Cart(user, product, 0));

        cart.setQuantity(cart.getQuantity() + quantity);

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getUserCart(Long userId) {
        return cartRepository.findByUser_Id(userId);
    }

    @Override
    public void removeFromCart(Long cartId) {
        if (!cartRepository.existsById(cartId)) {
            throw new CustomExceptions.ProductNotFoundException("Cart item not found");
        }
        cartRepository.deleteById(cartId);
    }

    @Override
    public void clearCartByUser(Long userId) {
        cartRepository.deleteByUser_Id(userId);
    }
}
