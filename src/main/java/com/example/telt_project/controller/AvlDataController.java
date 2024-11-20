package com.example.telt_project.controller;

import com.example.telt_project.model.avlData;
import com.example.telt_project.service.TeltonikaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avldata")
public class AvlDataController {

    private final TeltonikaDataService vehicleDataService;

    @Autowired
    public AvlDataController(TeltonikaDataService vehicleDataService) {
        this.vehicleDataService = vehicleDataService;
    }

    // Get all AVL data
    @GetMapping
    public List<avlData> getAllAvlData() {
        return vehicleDataService.getAllAvlData();
    }

    // Get AVL data by ID
    @GetMapping("/{id}")
    public ResponseEntity<avlData> getAvlDataById(@PathVariable Long id) {
        Optional<avlData> data = vehicleDataService.getDataById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Save new AVL data
    @PostMapping
    public avlData saveAvlData(@RequestBody avlData vehicleData) {
        return vehicleDataService.saveData(vehicleData);
    }

    // Update existing AVL data
    @PutMapping("/{id}")
    public ResponseEntity<avlData> updateAvlData(@PathVariable Long id, @RequestBody avlData vehicleData) {
        Optional<avlData> existingData = vehicleDataService.getDataById(id);
        if (existingData.isPresent()) {
            avlData updatedData = existingData.get();
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

    // Delete AVL data by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvlData(@PathVariable Long id) {
        if (vehicleDataService.getDataById(id).isPresent()) {
            vehicleDataService.deleteData(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/gps-data")
    public List<Map<String, Object>> getGpsData() {
        return vehicleDataService.getGpsData();
    }
    
    // get avl data by device id
    @GetMapping("/device/{deviceId}")
    public ResponseEntity< List<avlData>> getAvlDataByDeviceId(@PathVariable Long deviceId) {
        List<avlData> data = vehicleDataService.getAvlDataByDeviceId(deviceId);
        return ResponseEntity.ok(data);
    }
}
