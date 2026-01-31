package com.example.amazonclone.service.impl;

import com.example.amazonclone.entity.Product;
import com.example.amazonclone.entity.User;
import com.example.amazonclone.exception.CustomExceptions;
import com.example.amazonclone.repository.ProductRepository;
import com.example.amazonclone.repository.UserRepository;
import com.example.amazonclone.service.ProductService;
import org.springframework.stereotype.Service;
import com.example.amazonclone.entity.Role;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Product addProduct(Long sellerId, Product product) {

        User seller = userRepository.findById(sellerId)
            .orElseThrow(() ->
                    new CustomExceptions.UserNotFoundException(
                            "Seller not found with id " + sellerId
                    )
            );

    if (seller.getRole() != Role.SELLER) {
        throw new CustomExceptions.UnauthorizedAccessException(
                "Only sellers can add products"
        );
    }

    product.setSeller(seller);
    return productRepository.save(product);
}


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsBySeller(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new CustomExceptions.ProductNotFoundException(
                                "Product not found with id " + productId));
    }
}
