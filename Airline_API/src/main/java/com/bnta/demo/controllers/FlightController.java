package com.bnta.demo.controllers;

import com.bnta.demo.models.Flight;
import com.bnta.demo.models.FlightDTO;
import com.bnta.demo.models.Passenger;
import com.bnta.demo.models.PassengerDTO;
import com.bnta.demo.repositories.FlightRepository;
import com.bnta.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    FlightRepository flightRepository;
    @PostMapping(value = "/flight") // add new passenger
    public ResponseEntity<Flight> addFlight(@RequestBody FlightDTO flightDTO) {
        Flight addedFlight = flightService.addFlight(flightDTO);
        return new ResponseEntity<>(addedFlight, HttpStatus.CREATED);
    }
    @GetMapping // display all flights
    public ResponseEntity<List<Flight>> displayAllFlights() {
        return new ResponseEntity<>(flightRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")// display details of a specific flight
    public ResponseEntity<Optional<Flight>> getSpecificFlight(@PathVariable Long id) {
        return new ResponseEntity<>(flightRepository.findById(id), HttpStatus.OK);
    }
}
