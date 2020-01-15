package com.schedule.schedule.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="texts")
public class Text {
    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="shift_id")
    private Shift shift;


    public Text(Employee employee, Client client, Shift shift) {
        this.employee = employee;
        this.client = client;
        this.shift = shift;
    }

    public Text() {}

    // GETTERS only

    public long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Client getClient() {
        return client;
    }

    public Shift getShift() {
        return shift;
    }
}

