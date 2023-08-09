package com.bnta.demo.controllers;

import com.bnta.demo.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passengers")
public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @PostMapping // add a new passenger

    @GetMapping // display all passengers

    @GetMapping // display details of a specific passenger

    @PostMapping // book a passenger onto a flight
}
