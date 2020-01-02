package com.schedule.schedule.model;
import javax.persistence.*;

@Entity         // Admin class is an entity and is mapped to a database table
@Table(name = "admin")
public class Admin {
    @Id     // specifies the PK of an entity
    private Long id;

    private String name;
    private String cell;
    private String email;
    private String address;

    public Admin(Long id, String name, String cell, String email, String address) {
        // used when receiving data from user input, to save to the admin table in postgres
        this.id = id;
        this.name = name;
        this.cell = cell;
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

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
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
