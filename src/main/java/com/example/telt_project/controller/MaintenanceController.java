package com.example.telt_project.controller;

import com.example.telt_project.DTO.MaintenanceWithOdometerDTO;
import com.example.telt_project.model.Maintenance;
import com.example.telt_project.service.TeltonikaDataService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final TeltonikaDataService maintenanceService;

    public MaintenanceController(TeltonikaDataService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenance() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenance());
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<List<Maintenance>> getMaintenanceByVehicleId(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceByVehicleId(vehicleId));
    }

    //post by vehicle id
    @PostMapping("/{vehicleId}")
    public ResponseEntity<Maintenance> saveMaintenance(@PathVariable Long vehicleId, @RequestBody Maintenance maintenance) {
        maintenance.setVehicleId(vehicleId);
        Maintenance savedMaintenance = maintenanceService.saveMaintenance(vehicleId, maintenance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMaintenance);
    }

    @DeleteMapping("/{maintenanceId}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable Long maintenanceId) {
        maintenanceService.deleteMaintenance(maintenanceId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-all/{vehicleId}")
    public ResponseEntity<Void> deleteAllMaintenance(@PathVariable Long vehicleId) {
        maintenanceService.deleteAllMaintenance(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/alerts/{vehicleId}")
    public ResponseEntity<Map<String, Object>> getMaintenanceAlertsWithOdometer(@PathVariable Long vehicleId) {
        Map<String, Object> response = maintenanceService.getMaintenanceAlertsWithOdometer(vehicleId);
        return ResponseEntity.ok(response);
    }


    // @GetMapping("/maintenance-with-odometer/{vehicleId}")
    // public ResponseEntity<List<MaintenanceWithOdometerDTO>>
    // getMaintenanceWithOdometer(@PathVariable Long vehicleId) {
    // List<MaintenanceWithOdometerDTO> maintenanceList =
    // maintenanceService.getMaintenanceWithOdometer(vehicleId);
    // return ResponseEntity.ok(maintenanceList);
    // }
}
