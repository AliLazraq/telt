package com.example.telt_project.controller;

import com.example.telt_project.model.CanData;
import com.example.telt_project.service.TeltonikaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candata")
public class CanDataController {

    private final TeltonikaDataService vehicleDataService;

    @Autowired
    public CanDataController(TeltonikaDataService vehicleDataService) {
        this.vehicleDataService = vehicleDataService;
    }

    // Get all CAN data
    @GetMapping
    public List<CanData> getAllCanData() {
        return vehicleDataService.getAllCanData();
    }

    // Get CAN data by ID
    @GetMapping("/{id}")
    public ResponseEntity<CanData> getCanDataById(@PathVariable Long id) {
        Optional<CanData> data = vehicleDataService.getCanDataById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Save new CAN data
    @PostMapping
    public CanData saveCanData(@RequestBody CanData canData) {
        return vehicleDataService.saveCanData(canData);
    }

    // Update existing CAN data
    @PutMapping("/{id}")
    public ResponseEntity<CanData> updateCanData(@PathVariable Long id, @RequestBody CanData canData) {
        Optional<CanData> existingData = vehicleDataService.getCanDataById(id);
        if (existingData.isPresent()) {
            CanData updatedData = existingData.get();
            updatedData.setAvlDataId(canData.getAvlDataId());
            updatedData.setCanId(canData.getCanId());
            updatedData.setValue(canData.getValue());
            vehicleDataService.saveCanData(updatedData);
            return ResponseEntity.ok(updatedData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete CAN data by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCanData(@PathVariable Long id) {
        if (vehicleDataService.getCanDataById(id).isPresent()) {
            vehicleDataService.deleteCanData(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
