package com.example.telt_project.controller;

import com.example.telt_project.model.VehicleData;
import com.example.telt_project.service.VehicleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avldata")
public class VehicleDataController {
    private final VehicleDataService vehicleDataService;

    @Autowired
    public VehicleDataController(VehicleDataService vehicleDataService) {
        this.vehicleDataService = vehicleDataService;
    }

    // Get all data
    @GetMapping
    public List<VehicleData> getAllData() {
        return vehicleDataService.getAllAvlData();  // Fetches all records from the avl_data table
    }

    // Get data by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleData> getDataById(@PathVariable Long id) {
        Optional<VehicleData> data = vehicleDataService.getDataById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Save new data
    @PostMapping
    public VehicleData saveData(@RequestBody VehicleData vehicleData) {
        return vehicleDataService.saveData(vehicleData);
    }

    // Update existing data
    @PutMapping("/{id}")
    public ResponseEntity<VehicleData> updateData(@PathVariable Long id, @RequestBody VehicleData vehicleData) {
        Optional<VehicleData> existingData = vehicleDataService.getDataById(id);
        if (existingData.isPresent()) {
            VehicleData updatedData = existingData.get();
            updatedData.setLatitude(vehicleData.getLatitude());
            updatedData.setLongitude(vehicleData.getLongitude());
            updatedData.setSpeed(vehicleData.getSpeed());
            updatedData.setDeviceId(vehicleData.getDeviceId());
            updatedData.setTimestamp(vehicleData.getTimestamp());
            updatedData.setPriority(vehicleData.getPriority());
            updatedData.setAltitude(vehicleData.getAltitude());
            updatedData.setAngle(vehicleData.getAngle());
            updatedData.setSatellites(vehicleData.getSatellites());
            updatedData.setEventIoId(vehicleData.getEventIoId());
            vehicleDataService.saveData(updatedData);
            return ResponseEntity.ok(updatedData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete data by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteData(@PathVariable Long id) {
        if (vehicleDataService.getDataById(id).isPresent()) {
            vehicleDataService.deleteData(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
