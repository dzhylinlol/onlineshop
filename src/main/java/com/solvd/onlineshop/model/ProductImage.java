package com.solvd.onlineshop.model;

public class ProductImage {

    private Long id;
    private Product product;
    private String imageUrl;

    public ProductImage() {
    }

    public ProductImage(Long id,
                        Product product,
                        String imageUrl) {
        this.id = id;
        this.product = product;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ProductImage(id=" + this.getId() +
                ", product=" + this.getProduct() +
                ", imageUrl=" + this.getImageUrl() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public Product getProduct() {
        return this.product;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
