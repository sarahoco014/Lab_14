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

    @PostMapping(value = "/addflight") // add flight, POST CREATE "/flights/addflight"
    public ResponseEntity<Flight> addFlight(@RequestBody FlightDTO flightDTO) {
        Flight addedFlight = flightService.addFlight(flightDTO);
        return new ResponseEntity<>(addedFlight, HttpStatus.CREATED);
    }

    @GetMapping // display all flights and filter by destination, GET INDEX "/flights"
    public ResponseEntity<List<Flight>> displayAllFlightsFilterDestination(
            @RequestParam(required = false, name = "flightDestination") String flightDestination) {

        List<Flight> filteredFlights;

        if(flightDestination != null) {
            filteredFlights = flightService.findAllFlightsByDestination(flightDestination);
        } else {
            filteredFlights = flightService.displayAllFlights();
            }
        if(filteredFlights.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(filteredFlights, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")// display details of a specific flight, GET SHOW "/flights/id"
    public ResponseEntity<Flight> getSpecificFlight(@PathVariable Long id) {
        Flight flight = flightService.displaySpecificFlight(id);

        if(flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}") // cancel flight, POST DELETE "/flights/id
    public ResponseEntity<String> cancelFlight(@PathVariable Long id) {
        String cancelledFlight = flightService.cancelFlight(id);
        return new ResponseEntity<>(cancelledFlight, HttpStatus.OK);
    }
}
