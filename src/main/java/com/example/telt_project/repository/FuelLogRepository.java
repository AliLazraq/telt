package com.example.telt_project.repository;

import com.example.telt_project.model.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelLogRepository extends JpaRepository<FuelLog, Long> {

    List<FuelLog> findByVehicleId(Long vehicleId);
    FuelLog findTopByVehicleIdOrderByDateDesc(Long vehicleId);

    // Additional custom queries can go here if needed
}
