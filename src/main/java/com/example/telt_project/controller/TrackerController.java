package com.example.telt_project.controller;

import com.example.telt_project.model.Tracker;
import com.example.telt_project.model.avlData;
import com.example.telt_project.repository.TrackerRepository;
import com.example.telt_project.service.TeltonikaDataService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracker")
public class TrackerController {

    @Autowired
    private TeltonikaDataService trackerService;

    // Endpoint to get all tracker records
    @GetMapping
    public List<Tracker> getAllTrackers() {
        return trackerService.getAllTrackers();
    }

    // Endpoint to get tracker record by vehicle id
    @GetMapping("/{vehicleId}")
    public List<Tracker> getTrackerByVehicleId(@PathVariable Long vehicleId) {
        return trackerService.getTrackerByVehicleId(vehicleId);
    }

    // Endpoint to add a new tracker record
    @PostMapping
    public ResponseEntity<Tracker> createTracker(@RequestBody Tracker tracker) {
        try {
            Tracker savedTracker = trackerService.save(tracker);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTracker);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Delete all tracker records
    @DeleteMapping
    public ResponseEntity<Void> deleteAllTrackers() {
        try {
            trackerService.deleteAll();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete tracker by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTracker(@PathVariable Long id) {
        try {
            trackerService.deleteTracker(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
