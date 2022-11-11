package bern.project.travellog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bern.project.travellog.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByTravelId(long travelId);
}