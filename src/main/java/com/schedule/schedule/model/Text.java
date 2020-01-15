package com.schedule.schedule.model;

import javax.persistence.*;

@Entity
@Table(name="texts")
public class Text {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name="texts_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="texts_id_seq")
    private long id;

    @Column(name="uuid")
    private String uuid;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name="shift_id")
    private Shift shift;


    public Text(String uuid, Employee employee, Client client, Shift shift) {
        this.uuid = uuid;
        this.employee = employee;
        this.client = client;
        this.shift = shift;
    }

    public Text() {}

    // GETTERS only

    public long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
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

