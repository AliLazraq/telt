package com.example.telt_project.service;

import com.example.telt_project.model.VehicleData;
import com.example.telt_project.repository.VehicleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleDataService {

    private final VehicleDataRepository repository;

    @Autowired
    public VehicleDataService(VehicleDataRepository repository) {
        this.repository = repository;
    }

    // Method to fetch all records from avl_data table
    public List<VehicleData> getAllAvlData() {
        return repository.findAll();
    }

    // Method to fetch a single record by ID
    public Optional<VehicleData> getDataById(Long id) {
        return repository.findById(id);
    }

    // Method to save a new record or update an existing one
    public VehicleData saveData(VehicleData vehicleData) {
        return repository.save(vehicleData);
    }

    // Method to delete a record by ID
    public void deleteData(Long id) {
        repository.deleteById(id);
    }
}
