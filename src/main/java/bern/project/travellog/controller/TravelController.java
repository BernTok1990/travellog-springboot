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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bern.project.travellog.model.Travel;
import bern.project.travellog.repository.TravelRepository;

@CrossOrigin(origins = {"https://bern-travellog-frontend.herokuapp.com", "http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class TravelController {

    @Autowired
    TravelRepository travelRepository;

    @GetMapping("/travels/{user_id}")
    public ResponseEntity<List<Travel>> getAllTravels(@PathVariable("user_id") long userId) {
        try {
            List<Travel> travels = new ArrayList<Travel>();

            travelRepository.findByUserId(userId).forEach(travels::add);

            if (travels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(travels, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/travels/id/{travel_id}")
    public ResponseEntity<Travel> getTravelById(@PathVariable("travel_id") long id) {
        Optional<Travel> travelData = travelRepository.findById(id);

        if (travelData.isPresent()) {
            return new ResponseEntity<>(travelData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/travels")
    public ResponseEntity<Travel> createTravel(@RequestBody Travel travel) {
        try {
            Travel _travel = travelRepository.save(new Travel(
                travel.getName(), travel.getItinerary(), travel.getUserId()));
            return new ResponseEntity<>(_travel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/travels/{id}")
    public ResponseEntity<List<Travel>> updateTravel(@PathVariable("id") long id, @RequestBody Travel travel) {
        Optional<Travel> travelData = travelRepository.findById(id);

        if (travelData.isPresent()) {
            Travel _travel = travelData.get();
            _travel.setName(travel.getName());
            _travel.setItinerary(travel.getItinerary());
            travelRepository.save(_travel); // In ResponseEntity?
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/travels/{id}")
    public ResponseEntity<HttpStatus> deleteTravel(@PathVariable("id") long id) {
        try {
            travelRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}