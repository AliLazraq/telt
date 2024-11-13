package com.example.telt_project.repository;

import com.example.telt_project.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    long countByIsActive(int isActive);
}
