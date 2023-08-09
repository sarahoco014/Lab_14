package com.bnta.demo.controllers;

import com.bnta.demo.models.Flight;
import com.bnta.demo.models.Passenger;
import com.bnta.demo.models.PassengerDTO;
import com.bnta.demo.repositories.FlightRepository;
import com.bnta.demo.repositories.PassengerRepository;
import com.bnta.demo.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("passengers")
public class PassengerController {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    PassengerService passengerService;

    @Autowired
    FlightRepository flightRepository;

    @PostMapping(value = "/passenger") // add new passenger
    public ResponseEntity<Passenger> addPassenger(@RequestBody PassengerDTO passengerDTO) {
        Passenger addedPassenger = passengerService.addPassenger(passengerDTO);
        return new ResponseEntity<>(addedPassenger, HttpStatus.CREATED);
    }

    @GetMapping // display all passengers
    public ResponseEntity<List<Passenger>> displayAllPassengers() {
        return new ResponseEntity<>(passengerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")// display details of a specific passenger
    public ResponseEntity<Optional<Passenger>> getSpecificPassenger(@PathVariable Long id) {
        return new ResponseEntity<>(passengerRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/book") // book a passenger onto a flight
    public ResponseEntity<String> bookPassengerOntoFlight(
            @PathVariable Long passengerId,
            @PathVariable Long flightId) {

        String bookPassenger = passengerService.bookPassengerOntoFlight(passengerId, flightId);

        return new ResponseEntity<>(bookPassenger, HttpStatus.OK);

    }
}

