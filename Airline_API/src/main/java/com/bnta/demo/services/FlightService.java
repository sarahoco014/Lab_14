package com.bnta.demo.services;

import com.bnta.demo.models.Flight;
import com.bnta.demo.models.FlightDTO;
import com.bnta.demo.models.Passenger;
import com.bnta.demo.models.PassengerDTO;
import com.bnta.demo.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public Flight addFlight(FlightDTO flightDTO){
        Flight flight = new Flight(flightDTO.getDestination(), flightDTO.getCapacity(), flightDTO.getDepartureDate(), flightDTO.getDepartureTime());
        return flightRepository.save(flight);
}
