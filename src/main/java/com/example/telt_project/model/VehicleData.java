package com.example.telt_project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "avl_data")  // match the table name
public class VehicleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avl_data_id")  // match the column name
    private Long avlDataId;

    @Column(name = "device_id")
    private Integer deviceId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "priority")
    private Byte priority;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "altitude")
    private Short altitude;

    @Column(name = "angle")
    private Short angle;

    @Column(name = "satellites")
    private Byte satellites;

    @Column(name = "speed")
    private Short speed;

    @Column(name = "event_io_id")
    private Short eventIoId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and Setters
    public Long getAvlDataId() {
        return avlDataId;
    }

    public void setAvlDataId(Long avlDataId) {
        this.avlDataId = avlDataId;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Byte getPriority() {
        return priority;
    }

    public void setPriority(Byte priority) {
        this.priority = priority;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Short getAltitude() {
        return altitude;
    }

    public void setAltitude(Short altitude) {
        this.altitude = altitude;
    }

    public Short getAngle() {
        return angle;
    }

    public void setAngle(Short angle) {
        this.angle = angle;
    }

    public Byte getSatellites() {
        return satellites;
    }

    public void setSatellites(Byte satellites) {
        this.satellites = satellites;
    }

    public Short getSpeed() {
        return speed;
    }

    public void setSpeed(Short speed) {
        this.speed = speed;
    }

    public Short getEventIoId() {
        return eventIoId;
    }

    public void setEventIoId(Short eventIoId) {
        this.eventIoId = eventIoId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
