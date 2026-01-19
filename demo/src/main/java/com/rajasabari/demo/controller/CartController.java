package com.rajasabari.demo.controller;

import com.rajasabari.demo.dto.CartItemDto;
import com.rajasabari.demo.model.CartItem;
import com.rajasabari.demo.model.Product;
import com.rajasabari.demo.model.User;
import com.rajasabari.demo.repository.CartRepository;
import com.rajasabari.demo.repository.ProductRepository;
import com.rajasabari.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // Temporary user fetch (replace with JWT later)
    private User getUser() {
        return userRepository.findAll().get(0);
    }

    @PostMapping
    public CartItem addToCart(@RequestBody CartItemDto dto) {
        User user = getUser();
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem cartItem = new CartItem();
        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(dto.getQuantity());

        return cartRepository.save(cartItem);
    }

    @GetMapping
    public List<CartItem> getCartItems() {
        return cartRepository.findByUser(getUser());
    }

    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable Long id) {
        cartRepository.deleteById(id);
        return "Item removed from cart";
    }
}
