package com.solvd.onlineshop.model;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String material;
    private ProductCategory productCategory;

    public Product() {
    }

    public Product(Long id,
                   String name,
                   String description,
                   Double price,
                   String material,
                   ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.material = material;
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "Product(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", material=" + this.getMaterial() + ", productCategoryId=" + this.getProductCategory() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getMaterial() {
        return this.material;
    }

    public ProductCategory getProductCategory() {
        return this.productCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }


}
