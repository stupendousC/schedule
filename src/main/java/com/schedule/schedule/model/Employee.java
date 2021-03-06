package com.schedule.schedule.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "employees")
public class Employee {
    @Id
    @SequenceGenerator(name="employees_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employees_id_seq")
    private long id;
    private String oauthid;
    private String uuid;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Boolean active;

    public Employee(String oauthid, String uuid, String name, String address, String phone, String email, Boolean active) {
        this.oauthid = oauthid;
        this.uuid = uuid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.active = true;
    }

    public Employee() {}



    // GETTER & SETTERS
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUuid() {
        return uuid;
    }

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

    public long getId() {
        return id;
    }

    public String getOauthid() {
        return oauthid;
    }

    public void setOauthid(String oauthid) {
        this.oauthid = oauthid;
    }

}



