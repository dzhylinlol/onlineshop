package com.solvd.onlineshop.model;

public class PersonalDiscount {

    private Long id;
    private PersonalDiscountReason personalDiscountReason;
    private Double value;

    public PersonalDiscount() {
    }

    public PersonalDiscount(Long id,
                            PersonalDiscountReason personalDiscountReason,
                            Double value) {
        this.id = id;
        this.personalDiscountReason = personalDiscountReason;
        this.value = value;
    }

    @Override
    public String toString() {
        return "PersonalDiscount(id=" + this.getId() +
                ", personalDiscountReason=" + this.getPersonalDiscountReason() +
                ", value=" + this.value + ")";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonalDiscountReason getPersonalDiscountReason() {
        return personalDiscountReason;
    }

    public void setPersonalDiscountReason(PersonalDiscountReason personalDiscountReason) {
        this.personalDiscountReason = personalDiscountReason;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}