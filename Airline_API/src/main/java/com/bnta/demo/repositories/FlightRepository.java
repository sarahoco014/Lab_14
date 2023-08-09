package com.bnta.demo.repositories;
import com.bnta.demo.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}

