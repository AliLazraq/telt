package com.example.telt_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "city_geofencing")
public class CityGeofencing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "geofence_id")
    private Long geofenceId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "center_latitude", nullable = false)
    private Double centerLatitude;

    @Column(name = "center_longitude", nullable = false)
    private Double centerLongitude;

    @Column(name = "radius", nullable = false)
    private Double radius;

    // Getters and Setters
    public Long getGeofenceId() {
        return geofenceId;
    }

    public void setGeofenceId(Long geofenceId) {
        this.geofenceId = geofenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCenterLatitude() {
        return centerLatitude;
    }

    public void setCenterLatitude(Double centerLatitude) {
        this.centerLatitude = centerLatitude;
    }

    public Double getCenterLongitude() {
        return centerLongitude;
    }

    public void setCenterLongitude(Double centerLongitude) {
        this.centerLongitude = centerLongitude;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
