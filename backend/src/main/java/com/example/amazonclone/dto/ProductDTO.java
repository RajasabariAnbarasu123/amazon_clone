package com.example.amazonclone.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private Double price;

    @NotBlank(message = "Image URL is required")
    private String image;

    private Long sellerId;

    public ProductDTO() {}

    public ProductDTO(Long id, String name, String description, Double price, String image, Long sellerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.sellerId = sellerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public Double getPrice() {
        return price;
    }
 
    public void setPrice(Double price) {
        this.price = price;
    }
 
    public String getImage() {
        return image;
    }
 
    public void setImage(String image) {
        this.image = image;
    }

    public Long getSellerId() {
        return sellerId;
    }
 
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
