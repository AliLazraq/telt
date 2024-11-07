package com.example.telt_project.repository;

import com.example.telt_project.model.VehicleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDataRepository extends JpaRepository<VehicleData, Long> {
}