package com.example.telt_project.controller;

import com.example.telt_project.model.FuelData;
import com.example.telt_project.service.TeltonikaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fueldata")
public class FuelDataController {
    
        private final TeltonikaDataService vehicleDataService;
    
        @Autowired
        public FuelDataController(TeltonikaDataService vehicleDataService) {
            this.vehicleDataService = vehicleDataService;
        }
    
        // Get all fuel data
        @GetMapping
        public List<FuelData> getAllFuelData() {
            return vehicleDataService.getAllFuelData();
        }
    
        // Get fuel data by ID
        @GetMapping("/{id}")
        public ResponseEntity<FuelData> getFuelDataById(@PathVariable Long id) {
            Optional<FuelData> data = vehicleDataService.getFuelDataById(id);
            return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
    
        // Save new fuel data
        @PostMapping
        public FuelData saveFuelData(@RequestBody FuelData fuelData) {
            return vehicleDataService.saveFuelData(fuelData);
        }
    
        // Update existing fuel data
        @PutMapping("/{id}")
        public ResponseEntity<FuelData> updateFuelData(@PathVariable Long id, @RequestBody FuelData fuelData) {
            Optional<FuelData> existingData = vehicleDataService.getFuelDataById(id);
            if (existingData.isPresent()) {
                FuelData updatedData = existingData.get();
                updatedData.setAvlDataId(fuelData.getAvlDataId());
                updatedData.setFuelLevel(fuelData.getFuelLevel());
                updatedData.setFuelRate(fuelData.getFuelRate());
                vehicleDataService.saveFuelData(updatedData);
                return ResponseEntity.ok(updatedData);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    
        // Delete fuel data by ID
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteFuelData(@PathVariable Long id) {
            vehicleDataService.deleteFuelData(id);
            return ResponseEntity.noContent().build();
        }
}
