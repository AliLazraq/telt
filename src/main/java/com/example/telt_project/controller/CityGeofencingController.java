package com.example.telt_project.controller;

import com.example.telt_project.DTO.DeviceGeofenceRequestDTO;
import com.example.telt_project.model.CityGeofencing;
import com.example.telt_project.service.TeltonikaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city-geofencing")
public class CityGeofencingController {

    @Autowired
    private TeltonikaDataService teltonikaDataService;

    // Endpoint to get all city geofences
    @GetMapping
    public List<CityGeofencing> getAllCityGeofences() {
        return teltonikaDataService.getAllGeofences();
    }

    // Endpoint to check breach for a city geofence
    @PostMapping("/check-breach")
    public String checkGeofenceBreach(@RequestBody DeviceGeofenceRequestDTO request) {
        Long deviceId = request.getDeviceId();
        String cityName = request.getCityName();
        teltonikaDataService.checkCityGeofenceBreach(deviceId, cityName);
        return "Geofence breach check completed for device " + deviceId;
    }

}
