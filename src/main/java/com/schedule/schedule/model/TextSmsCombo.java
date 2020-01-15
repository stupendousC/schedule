package com.schedule.schedule.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextSmsCombo {
    private String phoneNumber;
    private String message;
    private long id;
    private Client client;
    private Shift shift;
    private Employee employee;

    public TextSmsCombo(@JsonProperty("phoneNumber") String phoneNumber,
                        @JsonProperty("message") String message,
                        @JsonProperty("id") long id,
                        @JsonProperty("client") Client client,
                        @JsonProperty("shift") Shift shift,
                        @JsonProperty("employee") Employee employee) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.id = id;
        this.client = client;
        this.shift = shift;
        this.employee = employee;
    }

    public TextSmsCombo() {}

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Shift getShift() {
        return shift;
    }

    public Employee getEmployee() {
        return employee;
    }
}

