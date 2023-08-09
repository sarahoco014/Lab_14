package com.bnta.demo.services;

import com.bnta.demo.models.Flight;
import com.bnta.demo.models.Passenger;
import com.bnta.demo.models.PassengerDTO;
import com.bnta.demo.repositories.FlightRepository;
import com.bnta.demo.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightService flightService;
    public Passenger addPassenger(PassengerDTO passengerDTO){

        Passenger passenger = new Passenger(passengerDTO.getName(), passengerDTO.getEmailAddress());

        for(Long flightId : passengerDTO.getFlightIds()) {
            Flight flight = flightRepository.findById(flightId).get();

            passenger.addFlight(flight);
        }
        return passengerRepository.save(passenger);
    }

    public List<Passenger> displayAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger displaySpecificPassenger(Long id) {
        return passengerRepository.findById(id).get();
    }

    public String bookPassengerOntoFlight(Long passengerId, Long flightId) {
        Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerId);
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);

        if(optionalPassenger.isPresent() && optionalFlight.isPresent()) {
            Passenger passenger = optionalPassenger.get();
            Flight flight = optionalFlight.get();

            passenger.getFlights().add(flight);
            flight.getPassengers().add(passenger);

            passengerRepository.save(passenger);
            flightRepository.save(flight);

            return "Your booking was successful.";
        } else {
            return "Your booking was not successful. Please try again.";
        }
    }
}
