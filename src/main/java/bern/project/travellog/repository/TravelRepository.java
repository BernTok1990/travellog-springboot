package bern.project.travellog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bern.project.travellog.model.Travel;

public interface TravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findByUserId(long userId);
}