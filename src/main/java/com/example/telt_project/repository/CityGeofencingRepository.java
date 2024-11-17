// CityGeofencingRepository.java
package com.example.telt_project.repository;

import com.example.telt_project.model.CityGeofencing;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityGeofencingRepository extends JpaRepository<CityGeofencing, Long> {
    Optional<CityGeofencing> findByName(String name); // To fetch a geofence by city name
}
