package com.solvd.onlineshop.model;

public class StorageProduct {
    private Long id;
    private Storage storage;
    private Product product;
    private Integer quantity;

    public StorageProduct() {
    }

    public StorageProduct(Long id,
                          Storage storage,
                          Product product,
                          Integer quantity) {
        this.id = id;
        this.storage = storage;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public Product getProduct() {
        return this.product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}