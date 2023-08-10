package com.bnta.demo.components;

import com.bnta.demo.models.Flight;
import com.bnta.demo.models.Passenger;
import com.bnta.demo.repositories.FlightRepository;
import com.bnta.demo.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception {

        LocalDate departureDate = LocalDate.of(2023, 8, 10);
        LocalTime departureTime = LocalTime.of(15, 30, 45);

    // EasyJet Flight
        Flight easyJet = new Flight("Madrid", 200, departureDate, departureTime);
        flightRepository.save(easyJet);

        Passenger sarah = new Passenger("Sarah", "123@gmail.com");
        sarah.addFlight(easyJet);
        passengerRepository.save(sarah);

        Passenger emma = new Passenger("Emma", "234@gmail,com");
        emma.addFlight(easyJet);
        passengerRepository.save(emma);

    // British Airways Flight
        Flight britishAirways = new Flight("Paris", 350, departureDate, departureTime);
        flightRepository.save(britishAirways);

        Passenger chris = new Passenger("Chris", "345@gmail.com");
        chris.addFlight(britishAirways);
        passengerRepository.save(chris);

        Passenger brian = new Passenger("Brian", "456@gmail.com");
        brian.addFlight(britishAirways);
        passengerRepository.save(brian);

    // Emirates Flight
        Flight emirates = new Flight("Emirates", 300, departureDate, departureTime);
        flightRepository.save(emirates);

        Passenger tanya = new Passenger("Tanya", "567@gmail.com");
        tanya.addFlight(emirates);
        passengerRepository.save(tanya);

    // Passengers
        Passenger lily = new Passenger("Lily", "678@gmail.com");
        lily.addFlight(emirates);
        lily.addFlight(britishAirways);
        passengerRepository.save(lily);

        Passenger jack = new Passenger("Jack", "321@gmail.com");
        jack.addFlight(easyJet);
        jack.addFlight(emirates);
        passengerRepository.save(jack);

    }
}

