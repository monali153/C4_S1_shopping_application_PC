package com.example.practice.UserProductService.model;

import org.springframework.data.annotation.Id;


public class Product {

    @Id
    private int productId;
    private String productName;
    private int rating;

    public Product() {
    }

    public Product(int productId, String productName, int rating) {
        this.productId = productId;
        this.productName = productName;
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", rating=" + rating +
                '}';
    }
}
