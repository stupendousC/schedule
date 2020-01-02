package com.schedule.schedule.model;
import javax.persistence.*;

@Entity         // Admin class is an entity and is mapped to a database table
@Table(name = "admin")  // only necessary if table name is not the same as class name, case matters!
public class Admin {
    @Id     // specifies the PK of an entity
    @Column(name = "id")       // redundant line bc dbObj.colName = javaObj.attribName
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String address;

    public Admin(Long id, String name, String phone, String email, String address) {
        // used when receiving data from user input, to save to the admin table in postgres
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Admin() {
        // used when receiving data from postgres, to map to an Admin object here
    }

    // GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}