package com.schedule.schedule.model;
import javax.persistence.*;

@Entity         // Admin class is an entity and is mapped to a database table
@Table(name = "admin")  // only necessary if table name is not the same as class name, case matters!
public class Admin {
    @Id     // specifies the PK of an entity
    @Column(name = "id")       // redundant line bc dbObj.colName = javaObj.attribName
    @SequenceGenerator(name="admin_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="admin_id_seq")
    private long id;
    private String oauthid;
    private String name;
    private String phone;
    private String email;
    private String address;

    public Admin(String oauthid, String name, String phone, String email, String address) {
        // used when receiving data from user input, to save to the admin table in postgres
        this.oauthid = oauthid;
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
