package com.example.telt_project.repository;


import com.example.telt_project.model.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, Long> {
    List<Tracker> findByVehicleId(Long vehicleId);
}

