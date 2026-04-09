package com.solvd.onlineshop.model;

public class Storage {
    private Long id;
    private String name;
    private String country;

    public Storage() {}

    public Storage(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public String toString() {
        return "Storage(id=" + this.getId() +
                ", name=" + this.getName() +
                ", country=" + this.getCountry() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}