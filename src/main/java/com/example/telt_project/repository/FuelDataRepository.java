package com.example.telt_project.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.telt_project.model.FuelData;

@Repository
public interface FuelDataRepository extends JpaRepository<FuelData, Long> {
}

