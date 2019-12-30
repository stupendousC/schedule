package com.schedule.schedule.model;

public class Employee {

    private final int id;
    private final String oAuth_id;
    private String name;
    private String address;
    private String cell;
    private String email;

    private static int currentAvailableId = 1;

    public Employee(String oAuth_id, String name, String address, String cell, String email) {
        this.oAuth_id = oAuth_id;
        this.name = name;
        this.address = address;
        this.cell = cell;
        this.email = email;

        this.id = assignID();
    }

    private int assignID() {
        this.currentAvailableId ++;
        return currentAvailableId - 1;
    }

    // getters(), may not use all of them
    public int getId() {
        return id;
    }

    public String getoAuth_id() {
        return oAuth_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCell() {
        return cell;
    }

    public String getEmail() {
        return email;
    }

    // setters(), for updatingEmployees
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

