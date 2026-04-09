package com.solvd.onlineshop.model;

public class ProductCategory {

    private Long id;
    private ProductCategoryType productCategoryType;

    public ProductCategory() {
    }

    public ProductCategory(Long id,
                           ProductCategoryType productCategoryType) {
        this.id = id;
        this.productCategoryType = productCategoryType;
    }

    @Override
    public String toString() {
        return "ProductCategory(id=" + this.getId() +
                ", productCategoryType=" + this.getProductCategoryType() + ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategoryType getProductCategoryType() {
        return productCategoryType;
    }

    public void setProductCategoryType(ProductCategoryType productCategoryType) {
        this.productCategoryType = productCategoryType;
    }
}



