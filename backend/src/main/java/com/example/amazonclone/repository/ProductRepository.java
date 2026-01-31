package com.example.amazonclone.repository;

import com.example.amazonclone.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Get all products by seller
    List<Product> findBySellerId(Long sellerId);
}
