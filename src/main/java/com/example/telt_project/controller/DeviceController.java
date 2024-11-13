package com.example.telt_project.controller;

import com.example.telt_project.model.Device;
import com.example.telt_project.service.TeltonikaDataService;
import com.example.telt_project.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final TeltonikaDataService vehicleDataService;
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(TeltonikaDataService vehicleDataService, DeviceRepository deviceRepository) {
        this.vehicleDataService = vehicleDataService;
        this.deviceRepository = deviceRepository;
    }

    // Get all devices
    @GetMapping
    public List<Device> getAllDevices() {
        return vehicleDataService.getAllDevices();
    }

    // Get device by ID
    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable int id) {
        Optional<Device> device = vehicleDataService.getDeviceById(id);
        return device.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Save new device
    @PostMapping
    public Device saveDevice(@RequestBody Device device) {
        return vehicleDataService.saveDevice(device);
    }

    // Update existing device
    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable int id, @RequestBody Device device) {
        Optional<Device> existingDevice = vehicleDataService.getDeviceById(id);
        if (existingDevice.isPresent()) {
            Device updatedDevice = existingDevice.get();
            updatedDevice.setDeviceImei(device.getDeviceImei());
            updatedDevice.setDeviceVehicleId(device.getDeviceVehicleId());
            updatedDevice.setIsActive(device.getIsActive());
            vehicleDataService.saveDevice(updatedDevice);
            return ResponseEntity.ok(updatedDevice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete device by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable int id) {
        if (vehicleDataService.getDeviceById(id).isPresent()) {
            vehicleDataService.deleteDevice(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status-count")
    public ResponseEntity<Map<String, Long>> getStatusCounts() {
        long activeCount = vehicleDataService.countByIsActive((short) 1); // Assuming 1 represents "active"
        long inactiveCount = vehicleDataService.countByIsActive((short) 0); // Assuming 0 represents "inactive"

        Map<String, Long> response = new HashMap<>();
        response.put("active", activeCount);
        response.put("inactive", inactiveCount);

        return ResponseEntity.ok(response);
    }
    
}
