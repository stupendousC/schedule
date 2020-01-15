package com.schedule.schedule.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// This is NOT meant to be used on its own.
// This is meant to be divided into 2 separate objects: Text and SmsRequest.
// I'm using this to receive the whole chunk of info from front end as a single api call per text.
public class TextSmsCombo {
    private String phoneNumber;
    private String message;
    private String uuid;
    private Client client;
    private Shift shift;
    private Employee employee;

    public TextSmsCombo(@JsonProperty("phoneNumber") String phoneNumber,
                        @JsonProperty("message") String message,
                        @JsonProperty("uuid") String id,
                        @JsonProperty("client") Client client,
                        @JsonProperty("shift") Shift shift,
                        @JsonProperty("employee") Employee employee) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.uuid = id;
        this.client = client;
        this.shift = shift;
        this.employee = employee;
    }

    public TextSmsCombo() {}

    // GETTERS
    public String getUuid() {
        return uuid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMessage() {
        return message;
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

