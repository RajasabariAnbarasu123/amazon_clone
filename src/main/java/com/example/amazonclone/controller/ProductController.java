package com.example.amazonclone.controller;

import com.example.amazonclone.entity.Product;
import com.example.amazonclone.repository.ProductRepository;
import com.example.amazonclone.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductController(ProductRepository productRepository,
                             UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // HOME PAGE PRODUCTS
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    // SELLER ADD PRODUCT
    @PostMapping("/add/{sellerId}")
    public ResponseEntity<Product> addProduct(
            @PathVariable Long sellerId,
            @RequestBody Product product) {

        product.setSeller(
                userRepository.findById(sellerId)
                        .orElseThrow(() -> new RuntimeException("Seller not found"))
        );

        return ResponseEntity.ok(productRepository.save(product));
    }
}