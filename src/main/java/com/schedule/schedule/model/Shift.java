package com.schedule.schedule.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="shifts")
public class Shift {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name="shifts_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="shifts_id_seq")
    private long id;
    private long employee_id;
    private long client_id;
    private LocalDate shift_date;
    private LocalTime start_time;
    private LocalTime end_time;

    public Shift(long employee_id, long client_id, LocalDate shift_date, LocalTime start_time, LocalTime end_time) {
        this.employee_id = employee_id;
        this.client_id = client_id;
        this.shift_date = shift_date;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Shift() {}




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

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public LocalDate getShift_date() {
        return shift_date;
    }

    public void setShift_date(LocalDate shift_date) {
        this.shift_date = shift_date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }
}
