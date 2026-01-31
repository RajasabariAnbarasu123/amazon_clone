package com.example.amazonclone.controller;

import com.example.amazonclone.entity.Cart;
import com.example.amazonclone.entity.Product;
import com.example.amazonclone.entity.User;
import com.example.amazonclone.repository.CartRepository;
import com.example.amazonclone.repository.ProductRepository;
import com.example.amazonclone.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartController(
            CartRepository cartRepository,
            UserRepository userRepository,
            ProductRepository productRepository
    ) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    // ADD/UPDATE CART
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity
    ) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            Cart cart = cartRepository
                    .findByUser_IdAndProduct_Id(userId, productId)
                    .orElse(new Cart(user, product, 0));

            cart.setQuantity(cart.getQuantity() + quantity);
            Cart savedCart = cartRepository.save(cart);
            
            return ResponseEntity.ok(savedCart);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", "error");
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // VIEW CART
    @GetMapping("/{userId}")
    public ResponseEntity<List<Cart>> getUserCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartRepository.findByUser_Id(userId));
    }

    // REMOVE ITEM
    @DeleteMapping("/item/{cartId}")
    public ResponseEntity<?> removeItem(@PathVariable Long cartId) {
        try {
            cartRepository.deleteById(cartId);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Item removed");
            response.put("status", "success");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", "error");
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // CLEAR CART
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<?> clearCart(@PathVariable Long userId) {
        try {
            cartRepository.deleteByUser_Id(userId);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Cart cleared");
            response.put("status", "success");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", "error");
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
