package com.solvd.onlineshop.model;

public class Campaign {

    private Long id;
    private String name;

    public Campaign() {
    }

    public Campaign(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Campaign(id=" + this.getId() +
                ", name=" + this.getName() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
