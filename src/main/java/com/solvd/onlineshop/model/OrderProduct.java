package com.solvd.onlineshop.model;


import java.util.List;

public class OrderProduct {
    private Long id;
    private Product product;
    private Order order;
    private Integer orderedQuantity;

    public OrderProduct() {
    }

    public OrderProduct(Long id,
                        Product product,
                        Order order,
                        Integer orderedQuantity) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.orderedQuantity = orderedQuantity;
    }

    public Long getId() {
        return this.id;
    }

    public Product getProducts() { return product; }

    public Order getOrder() {
        return this.order;
    }

    public Integer getOrderedQuantity() {
        return this.orderedQuantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducts(Product product) {
        this.product = product;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    public void setQuantity(Integer orderedQuantity) {
    this.orderedQuantity = orderedQuantity;}
}