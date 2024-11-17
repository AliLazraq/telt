package com.example.telt_project.controller;

import com.example.telt_project.model.Vehicles;
import com.example.telt_project.service.TeltonikaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehiclesController {
    
        private final TeltonikaDataService vehicleDataService;
    
        @Autowired
        public VehiclesController(TeltonikaDataService vehicleDataService) {
            this.vehicleDataService = vehicleDataService;
        }
    
        // Get all vehicles
        @GetMapping
        public List<Vehicles> getAllVehicles() {
            return vehicleDataService.getAllVehicles();
        }
    
        // Get vehicle by ID
        @GetMapping("/{id}")
        public ResponseEntity<Vehicles> getVehicleById(@PathVariable Long id) {
            Optional<Vehicles> vehicle = vehicleDataService.getVehicleById(id);
            return vehicle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
    
        // Save new vehicle
        @PostMapping
        public Vehicles saveVehicle(@RequestBody Vehicles vehicle) {
            return vehicleDataService.saveVehicle(vehicle);
        }
    
        // Update existing vehicle
        @PutMapping("/{id}")
        public ResponseEntity<Vehicles> updateVehicle(@PathVariable Long id, @RequestBody Vehicles vehicle) {
            Optional<Vehicles> existingVehicle = vehicleDataService.getVehicleById(id);
            if (existingVehicle.isPresent()) {
                Vehicles updatedVehicle = existingVehicle.get();
                updatedVehicle.setPlateNumber(vehicle.getPlateNumber());
                updatedVehicle.setMake(vehicle.getMake());
                updatedVehicle.setModel(vehicle.getModel());
                updatedVehicle.setYear(vehicle.getYear());
                updatedVehicle.setVin(vehicle.getVin());
                vehicleDataService.saveVehicle(updatedVehicle);
                return ResponseEntity.ok(updatedVehicle);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    
        // Delete vehicle by ID
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
            vehicleDataService.deleteVehicle(id);
            return ResponseEntity.noContent().build();
        }

        // Get vehicle count
        @GetMapping("/count")
        public ResponseEntity<Long> getVehicleCount() {
            long count = vehicleDataService.getVehicleCount();
            return ResponseEntity.ok(count);
    }
}
