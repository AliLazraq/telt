package com.example.telt_project.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.telt_project.model.Vehicles;

@Repository
public interface VehiclesRepository extends JpaRepository<Vehicles, Long> {
    @Query("SELECT COUNT(v) FROM Vehicles v")
    long countVehicles();
}

