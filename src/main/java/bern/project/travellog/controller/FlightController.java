package bern.project.travellog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bern.project.travellog.model.Flight;
import bern.project.travellog.repository.FlightRepository;

@CrossOrigin(origins = {"https://bern-travellog-frontend.herokuapp.com", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @GetMapping("/flights/{travel_id}")
    public ResponseEntity<List<Flight>> getAllFlights(@PathVariable("travel_id") Long travelId) {
        try {
            List<Flight> flights = new ArrayList<Flight>();

            if (travelId == null)
                flightRepository.findAll().forEach(flights::add);
            else
                flightRepository.findByTravelId(travelId).forEach(flights::add);

            if (flights.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/flights/id/{flight_id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("flight_id") long id) {
        Optional<Flight> flightData = flightRepository.findById(id);

        if (flightData.isPresent()) {
            return new ResponseEntity<>(flightData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        try {
            Flight _flight = flightRepository.save(new Flight(
                flight.getDate(), flight.getSource(), flight.getDestination(), flight.getTravelId()));
            return new ResponseEntity<>(_flight, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<HttpStatus> deleteFlight(@PathVariable("id") long id) {
        try {
            flightRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}