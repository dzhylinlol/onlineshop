package com.solvd.onlineshop.model;

import java.time.LocalDateTime;

public class Review {

    private Long id;
    private Product product;
    private Buyer buyer;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;

    public Review() {
    }

    public Review(Long id,
                  Product product,
                  Buyer buyer,
                  Integer rating,
                  String comment,
                  LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.buyer = buyer;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Review(id=" + this.getId() +
                ", product=" + this.getProduct() +
                ", buyer=" + this.getBuyer() +
                ", rating=" + this.getRating() +
                ", comment=" + this.getComment() +
                ", createdAt=" + this.getCreatedAt() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public Product getProduct() {
        return this.product;
    }

    public Buyer getBuyer() {
        return this.buyer;
    }

    public Integer getRating() {
        return this.rating;
    }

    public String getComment() {
        return this.comment;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
