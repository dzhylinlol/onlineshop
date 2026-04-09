package com.solvd.onlineshop.model;

public class Order {
    private Long id;
    private String confirmationId;
    private Double totalSum;
    private Double seasonalDiscount;
    private Double personalDiscount;
    private Seller seller;
    private Buyer buyer;

    public Order() {
    }

    public Order(Long id,
                 String confirmationId,
                 Double totalSum,
                 Double seasonalDiscount,
                 Double personalDiscount,
                 Seller seller, Buyer buyer) {
        this.id = id;
        this.confirmationId = confirmationId;
        this.totalSum = totalSum;
        this.seasonalDiscount = seasonalDiscount;
        this.personalDiscount = personalDiscount;
        this.seller = seller;
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Order(id=" + this.getId() +
                ", confirmationId=" + this.getConfirmationId() +
                ", totalSum=" + this.getTotalSum() +
                ", seasonalDiscount=" + this.getSeasonalDiscount() +
                ", personalDiscount=" + this.getPersonalDiscount() +
                ", seller=" + this.getSeller() +
                ", buyer=" + this.getBuyer() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public String getConfirmationId() {
        return this.confirmationId;
    }

    public Double getTotalSum() {
        return this.totalSum;
    }

    public Double getSeasonalDiscount() {
        return this.seasonalDiscount;
    }

    public Double getPersonalDiscount() {
        return this.personalDiscount;
    }

    public Seller getSeller() {
        return this.seller;
    }

    public Buyer getBuyer() {
        return this.buyer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public void setSeasonalDiscount(Double seasonalDiscount) {
        this.seasonalDiscount = seasonalDiscount;
    }

    public void setPersonalDiscount(Double personalDiscount) {
        this.personalDiscount = personalDiscount;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }


}


