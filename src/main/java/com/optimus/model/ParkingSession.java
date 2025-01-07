package com.optimus.model;

import java.sql.Timestamp;

public class ParkingSession {

    private String licensePlate;

    private Timestamp actTime;

    private Timestamp deactTime;

    private String username;

    public ParkingSession() {
    }

    public ParkingSession(String licensePlate, Timestamp actTime, Timestamp deactTime, String username) {
        this.licensePlate = licensePlate;
        this.actTime = actTime;
        this.deactTime = deactTime;
        this.username = username;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Timestamp getActTime() {
        return actTime;
    }

    public void setActTime(Timestamp actTime) {
        this.actTime = actTime;
    }

    public Timestamp getDeactTime() {
        return deactTime;
    }

    public void setDeactTime(Timestamp deactTime) {
        this.deactTime = deactTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
