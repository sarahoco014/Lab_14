package com.bnta.demo.services;

import com.bnta.demo.models.Flight;
import com.bnta.demo.models.FlightDTO;
import com.bnta.demo.models.Passenger;
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

    public List<Flight> findAllFlightsByDestination(String destination) {
        if(destination != null) {
            return flightRepository.findByDestination(destination);
        }
        return flightRepository.findAll();
    }

    public String cancelFlight(Long id) {
        Optional<Flight> optionalFlight = flightRepository.findById(id);

        if(optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();

            for(Passenger passenger : flight.getPassengers()) {
                passenger.getFlights().remove(flight);
            }

            flight.getPassengers().clear();

            flightRepository.delete(flight);

            return "Your flight has been cancelled successfully";
        } else {
            return "Flight not found";
        }
    }

}
