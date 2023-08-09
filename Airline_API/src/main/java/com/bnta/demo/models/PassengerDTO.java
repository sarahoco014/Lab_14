package com.bnta.demo.models;

import java.util.List;

public class PassengerDTO {

    private String name;
    private String emailAddress;
    private List<Long> flightIds;

    public PassengerDTO(String name, String emailAddress, List<Long> flightIds) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.flightIds = flightIds;
    }

    // default constructor

    public PassengerDTO(){}

    // getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Long> getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(List<Long> flightIds) {
        this.flightIds = flightIds;
    }
}
