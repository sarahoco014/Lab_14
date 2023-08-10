package com.bnta.demo.controllers;

import com.bnta.demo.models.Flight;
import com.bnta.demo.models.FlightDTO;
import com.bnta.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @PostMapping(value = "/flight") // add new passenger
    public ResponseEntity<Flight> addFlight(@RequestBody FlightDTO flightDTO) {
        Flight addedFlight = flightService.addFlight(flightDTO);
        return new ResponseEntity<>(addedFlight, HttpStatus.CREATED);
    }

    @GetMapping // display all flights and filter by destination
    public ResponseEntity<List<Flight>> displayAllFlightsFilterDestination(
            @RequestParam(required = false, name = "flightDestination") String flightDestination) {

        List<Flight> filteredFlights = flightService.findAllFlightsByDestination(flightDestination);

        if(filteredFlights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")// display details of a specific flight
    public ResponseEntity<Flight> getSpecificFlight(@PathVariable Long id) {
        Flight flight = flightService.displaySpecificFlight(id);

        if(flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PostMapping // cancel flight
    public ResponseEntity<String> cancelFlight(@PathVariable Flight flight) {
        String cancelledFlight = flightService.cancelFlight(flight);
        return new ResponseEntity<>(cancelledFlight, HttpStatus.CREATED);
    }
}
