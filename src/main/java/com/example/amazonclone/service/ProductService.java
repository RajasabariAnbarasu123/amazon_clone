package com.example.amazonclone.service;

import com.example.amazonclone.entity.Product;

import java.util.List;

public interface ProductService {

    // Add product by seller
    Product addProduct(Long sellerId, Product product);

    // Get all products (Home page)
    List<Product> getAllProducts();

    // Get products by seller
    List<Product> getProductsBySeller(Long sellerId);

    // Get product by ID
    Product getProductById(Long productId);
}
