package com.solvd.onlineshop.model;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class Seller {
    @XmlAttribute
    private Long id;
    @XmlElement
    private String name;
    @XmlElement
    private String country;
    @XmlElement
    private String licenceNumber;
    @XmlElement
    private String email;
    @XmlElement
    private String phone;

    public Seller() {
    }
    public Seller (Long id,
                   String name,
                   String country,
                   String licenceNumber) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.licenceNumber = licenceNumber;
    }

    @Override
    public String toString() {
        return "Seller(id=" + this.getId() + ", name=" + this.getName() + ", country=" + this.getCountry() + ", licenceNumber=" + this.getLicenceNumber() + ", email=" + this.getEmail() + ", phone=" + this.getPhone() + ")";
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

    public String getLicenceNumber() {
        return this.licenceNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
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

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
