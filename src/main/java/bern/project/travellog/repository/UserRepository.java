package bern.project.travellog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bern.project.travellog.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}