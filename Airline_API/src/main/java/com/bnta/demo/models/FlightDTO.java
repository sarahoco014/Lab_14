package com.bnta.demo.models;

import java.util.List;

public class FlightDTO {

    private String destination;
    private int capacity;
    private String departureDate;
    private String departureTime;
    private List<Long> flightIds;

    public FlightDTO(String destination, int capacity, String departureDate, String departureTime, List<Long> flightIds) {
        this.destination = destination;
        this.capacity = capacity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.flightIds = flightIds;
    }

    public FlightDTO(){}

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public List<Long> getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(List<Long> flightIds) {
        this.flightIds = flightIds;
    }
}
