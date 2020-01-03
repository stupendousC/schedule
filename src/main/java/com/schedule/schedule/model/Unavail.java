package com.schedule.schedule.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="unavails")
public class Unavail {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name="unavails_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="unavails_id_seq")
    private long id;
    private long employee_id;
    private LocalDate day_off;

    public Unavail(long employee_id, LocalDate day_off) {
        this.employee_id = employee_id;
        this.day_off = day_off;
    }

    public Unavail() {}

    // GETTERS & SETTERS

    public long getId() {
        return id;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public LocalDate getDay_off() {
        return day_off;
    }

    public void setDay_off(LocalDate day_off) {
        this.day_off = day_off;
    }
}
