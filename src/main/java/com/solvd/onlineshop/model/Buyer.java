package com.solvd.onlineshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Buyer {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;

    public Buyer() {
    }

    public Buyer(Long id, String firstName, String lastName, String email, String phone, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Buyer(id=" + this.getId() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", email=" + this.getEmail() + ", phone=" + this.getPhone() + ", dob=" + this.getDob() + ")";
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

}
