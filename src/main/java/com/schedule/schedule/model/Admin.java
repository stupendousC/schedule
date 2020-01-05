package com.schedule.schedule.model;
import javax.persistence.*;

@Entity         // Admin class is an entity and is mapped to a database table
@Table(name = "admins")  // only necessary if table name is not the same as class name, case matters!
public class Admin {
    @Id     // specifies the PK of an entity
    @Column(name = "id")       // redundant line bc dbObj.colName = javaObj.attribName
    @SequenceGenerator(name="admins_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admins_id_seq")
    private long id;
    private String oauthid;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Boolean active;

    public Admin(String oauthid, String name, String phone, String email, String address, Boolean active) {
        // used when receiving data from user input, to save to the admins table in postgres
        this.oauthid = oauthid;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.active = active;
    }

    public Admin() {
        // used when receiving data from postgres, to map to an Admin object here
    }




    // GETTERS & SETTERS

    public long getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getOauthid() {
        return oauthid;
    }

    public void setOauthid(String oauthid) {
        this.oauthid = oauthid;
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
