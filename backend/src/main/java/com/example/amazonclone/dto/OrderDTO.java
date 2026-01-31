package com.example.amazonclone.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class OrderDTO {

    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Seller ID is required")
    private Long sellerId;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @Min(value = 1, message = "Amount must be greater than zero")
    private Double amount;

    private String paymentStatus;

    private LocalDateTime createdAt;

    public OrderDTO() {}

    public OrderDTO(Long id, Long userId, Long sellerId, Long productId,
                    Integer quantity, Double amount,
                    String paymentStatus, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public Long getSellerId() {
        return sellerId;
    }
 
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
 
    public Long getProductId() {
        return productId;
    }
 
    public void setProductId(Long productId) {
        this.productId = productId;
    }
 
    public Integer getQuantity() {
        return quantity;
    }
 
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
 
    public Double getAmount() {
        return amount;
    }
 
    public void setAmount(Double amount) {
        this.amount = amount;
    }
 
    public String getPaymentStatus() {
        return paymentStatus;
    }
 
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
 
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
 
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
