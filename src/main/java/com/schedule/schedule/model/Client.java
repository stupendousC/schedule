package com.schedule.schedule.model;
import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name="clients_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="clients_id_seq")
    private long id;
    private String uuid;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Boolean active;

    public Client(String name, String uuid, String phone, String email, String address, Boolean active) {
        this.name = name;
        this.uuid = uuid;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.active = true;
    }

    public Client() {
    }

    // GETTERS & SETTERS

    public long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

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
