package com.example.telt_project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")

public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alertId;

    @Column(name = "geofence_id")
    private Long geofenceId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "breach_status", nullable = false)
    private String breachStatus;

    // Getters and Setters
    public Long getAlertId() {
        return alertId;
    }

    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    public Long getGeofenceId() {
        return geofenceId;
    }

    public void setGeofenceId(Long geofenceId) {
        this.geofenceId = geofenceId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getBreachStatus() {
        return breachStatus;
    }

    public void setBreachStatus(String breachStatus) {
        this.breachStatus = breachStatus;
    }

}