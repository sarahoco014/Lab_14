package com.bnta.demo.services;

import com.bnta.demo.models.Flight;
import com.bnta.demo.models.FlightDTO;
import com.bnta.demo.models.Passenger;
import com.bnta.demo.models.PassengerDTO;
import com.bnta.demo.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public Flight addFlight(FlightDTO flightDTO) {
        Flight flight = new Flight(flightDTO.getDestination(), flightDTO.getCapacity(), flightDTO.getDepartureDate(), flightDTO.getDepartureTime());
        return flightRepository.save(flight);
    }

    public List<Flight> displayAllFlights() {
        return flightRepository.findAll();
    }

    public Flight displaySpecificFlight(Long id) {
        return flightRepository.findById(id).get();
    }

    public String cancelFlight(Flight flight) {
        Optional<Flight> optionalFlight = flightRepository.findById(flight.getId());

        if(optionalFlight.isPresent()) {
            flightRepository.delete(optionalFlight.get());
            return "Your flight was successfully cancelled.";
        } else {
            return "Please try again.";
        }
    }

    public List<Flight> findAllFlightsByDestination(String destination) {
        return flightRepository.findByDestination(destination);
    }

}
