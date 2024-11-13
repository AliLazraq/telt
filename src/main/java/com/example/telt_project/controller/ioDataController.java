package com.example.telt_project.controller;

import com.example.telt_project.model.ioData;
import com.example.telt_project.service.TeltonikaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/iodata")
public class ioDataController {
        
            private final TeltonikaDataService vehicleDataService;
        
            @Autowired
            public ioDataController(TeltonikaDataService vehicleDataService) {
                this.vehicleDataService = vehicleDataService;
            }
        
            // Get all io data
            @GetMapping
            public List<ioData> getAllioData() {
                return vehicleDataService.getAllioData();
            }
        
            // Get io data by ID
            @GetMapping("/{id}")
            public ResponseEntity<ioData> getioDataById(@PathVariable Long id) {
                Optional<ioData> data = vehicleDataService.getioDataById(id);
                return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
            }
        
            // Save new io data
            @PostMapping
            public ioData saveioData(@RequestBody ioData ioData) {
                return vehicleDataService.saveioData(ioData);
            }
        
            // Update existing io data
            @PutMapping("/{id}")
            public ResponseEntity<ioData> updateioData(@PathVariable Long id, @RequestBody ioData ioData) {
                Optional<ioData> existingData = vehicleDataService.getioDataById(id);
                if (existingData.isPresent()) {
                    ioData updatedData = existingData.get();
                    updatedData.setAvlDataId(ioData.getAvlDataId());
                    updatedData.setIoElementId(ioData.getIoElementId());
                    updatedData.setValue(ioData.getValue());
                    vehicleDataService.saveioData(updatedData);
                    return ResponseEntity.ok(updatedData);
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
        
            // Delete io data by ID
            @DeleteMapping("/{id}")
            public ResponseEntity<Void> deleteioData(@PathVariable Long id) {
                vehicleDataService.deleteioData(id);
                return ResponseEntity.noContent().build();
            }
        
            // Delete all io data
            @DeleteMapping
            public ResponseEntity<Void> deleteAllioData() {
                vehicleDataService.deleteAllioData();
                return ResponseEntity.noContent().build();
            }
}
