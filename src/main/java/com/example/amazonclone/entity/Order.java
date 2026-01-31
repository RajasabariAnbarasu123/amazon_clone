package com.example.amazonclone.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String paymentStatus;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Order() {
        this.createdAt = LocalDateTime.now();
    }

    public Order(Long id, Long userId, Long sellerId, Long productId,
                 Integer quantity, Double amount,
                 String paymentStatus) {
        this.id = id;
        this.userId = userId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.createdAt = LocalDateTime.now();
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
}
