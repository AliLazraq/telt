package com.example.telt_project.controller;

import com.example.telt_project.model.FuelLog;
import com.example.telt_project.service.TeltonikaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel-logs")
public class FuelLogController {

    private final TeltonikaDataService fuelLogService;

    @Autowired
    public FuelLogController(TeltonikaDataService fuelLogService) {
        this.fuelLogService = fuelLogService;
    }

    @GetMapping
    public ResponseEntity<List<FuelLog>> getAllFuelLogs() {
        List<FuelLog> logs = fuelLogService.getAllFuelLogs(); 
        return ResponseEntity.ok(logs);
    }
    @PostMapping(consumes = "application/json")
    public FuelLog saveFuelLog(@RequestBody FuelLog fuelLog) {
        System.out.println("FuelLogController.saveFuelLog: fuelLog = " + fuelLog);
        return fuelLogService.saveFuelLog(fuelLog);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<List<FuelLog>> getFuelLogsByVehicleId(@PathVariable Long vehicleId) {
        List<FuelLog> logs = fuelLogService.getFuelLogsByVehicleId(vehicleId);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/log/{fuelLogId}")
    public ResponseEntity<FuelLog> getFuelLogById(@PathVariable Long fuelLogId) {
        return fuelLogService.getFuelLogById(fuelLogId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{fuelLogId}")
    public ResponseEntity<FuelLog> updateFuelLog(@PathVariable Long fuelLogId, @RequestBody FuelLog fuelLog) {
        FuelLog updatedLog = fuelLogService.updateFuelLog(fuelLogId, fuelLog);
        return ResponseEntity.ok(updatedLog);
    }

    //Delete all fuel logs
    @DeleteMapping
    public ResponseEntity<Void> deleteAllFuelLogs() {
        fuelLogService.deleteAllFuelLogs();
        return ResponseEntity.noContent().build();
    }

    //get latest odometer by vehicle id
    @GetMapping("/latest/{vehicleId}")
    public ResponseEntity<FuelLog> getLatestOdometerByVehicleId(@PathVariable Long vehicleId) {
        FuelLog fuelLog = fuelLogService.getLatestOdometerByVehicleId(vehicleId);
        return ResponseEntity.ok(fuelLog);
    }
}
