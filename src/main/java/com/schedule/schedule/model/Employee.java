package com.schedule.schedule.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "employees")
public class Employee {
    @Id
    private int id;
    private String oauthid;
    private String name;
    private String address;
    private String phone;
    private String email;

    public Employee(String oauthid, String name, String address, String phone, String email) {
        this.oauthid = oauthid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Employee() {}



    // GETTER & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getOauthid() {
        return oauthid;
    }

    public void setOauthid(String oauthid) {
        this.oauthid = oauthid;
    }
}
