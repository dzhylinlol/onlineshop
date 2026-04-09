package com.solvd.onlineshop.model;

public class OrderProduct {
    private Long id;
    private Product product;
    private Order order;
    private Integer quantity;

    public OrderProduct() {
    }

    public OrderProduct(Long id,
                        Product product,
                        Order order,
                        Integer quantity) {
        this.id = id;
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public Product getProduct() {
        return this.product;
    }

    public Order getOrder() {
        return this.order;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}