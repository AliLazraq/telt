package com.example.telt_project.controller;

import com.example.telt_project.service.TeltonikaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gps")
public class GpsController {

    @Autowired
    private TeltonikaDataService gpsDataService;

    @GetMapping("/latest")
    public ResponseEntity<List<Map<String, Object>>> getLatestGpsData() {
        List<Map<String, Object>> gpsData = gpsDataService.fetchLatestGpsData();
        return ResponseEntity.ok(gpsData);
    }
}
