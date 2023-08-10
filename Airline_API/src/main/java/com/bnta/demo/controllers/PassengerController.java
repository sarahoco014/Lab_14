package com.bnta.demo.controllers;

import com.bnta.demo.models.Passenger;
import com.bnta.demo.models.PassengerDTO;
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
    PassengerService passengerService;

    @PostMapping(value = "/passenger") // add new passenger
    public ResponseEntity<Passenger> addPassenger(@RequestBody PassengerDTO passengerDTO) {
        Passenger addedPassenger = passengerService.addPassenger(passengerDTO);
        return new ResponseEntity<>(addedPassenger, HttpStatus.CREATED);
    }

    @GetMapping // display all passengers
    public ResponseEntity<List<Passenger>> displayAllPassengers() {
        return new ResponseEntity<>(passengerService.displayAllPassengers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")// display details of a specific passenger
    public ResponseEntity<Passenger> getSpecificPassenger(@PathVariable Long id) {
        Optional<Passenger> optionalPassenger = passengerService.displaySpecificPassenger(id);

        if(optionalPassenger.isPresent()) {
            return new ResponseEntity<>(optionalPassenger.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/book") // book a passenger onto a flight
    public ResponseEntity<String> bookPassengerOntoFlight(
            @PathVariable Long passengerId,
            @PathVariable Long flightId) {

        String bookPassenger = passengerService.bookPassengerOntoFlight(passengerId, flightId);

        if(bookPassenger.equals("Your booking was successful!")) {
            return new ResponseEntity<>(bookPassenger, HttpStatus.OK);
        } else if(bookPassenger.equals("This flight is full, please choose another flight.")) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

