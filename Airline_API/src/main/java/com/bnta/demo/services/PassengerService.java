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

    public Passenger addPassenger(PassengerDTO passengerDTO) {

        Passenger passenger = new Passenger(passengerDTO.getName(), passengerDTO.getEmailAddress());

        for (Long flightId : passengerDTO.getFlightIds()) {
            Flight flight = flightRepository.findById(flightId).get();

            passenger.addFlight(flight);
        }
        return passengerRepository.save(passenger);
    }

    public List<Passenger> displayAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> displaySpecificPassenger(Long id) {
        return passengerRepository.findById(id);
    }

    public String bookPassengerOntoFlight(Long passengerId, Long flightId) { // adds functionality if flight is full
        Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerId);
        Optional<Flight> optionalFlight = flightRepository.findById(flightId);

        if (!optionalPassenger.isPresent() && !optionalFlight.isPresent()) {
            return "Your booking was not successful, please try again.";
        } else {
            Passenger passenger = optionalPassenger.get();
            Flight flight = optionalFlight.get();

            if (flight.getPassengers().size() >= flight.getCapacity()) {
                return "This flight is full, please choose another flight.";
            } else {
                passenger.getFlights().add(flight);
                flight.getPassengers().add(passenger);

                passengerRepository.save(passenger);
                flightRepository.save(flight);

                return"Your booking was successful!";
            }
        }
    }
}