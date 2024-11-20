package com.example.telt_project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle_maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id")
    private Long maintenanceId;

    @Column(nullable = false)
    private Long vehicleId;

    @Column(nullable = false)
    private String operationType; // Oil Change, Tyre Change, Distribution Change

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, updatable = false)
    private LocalDateTime maintenanceDate;


    @Column(name = "alert")
    private String alert;


    // Default constructor required by Hibernate
    public Maintenance() {}

    public Maintenance(Long maintenanceId, Long vehicleId, String operationType, Double price, LocalDateTime maintenanceDate, String alert) {
        this.maintenanceId = maintenanceId;
        this.vehicleId = vehicleId;
        this.operationType = operationType;
        this.price = price;
        this.maintenanceDate = maintenanceDate;
        this.alert = alert;
    }

    // Getters and Setters
    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDateTime maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    // Getters and Setters
    
    @PrePersist
    public void prePersist() {
        if (maintenanceDate == null) {
            maintenanceDate = LocalDateTime.now();
        }
        if (alert == null) {
            alert = "No Alert";
        }
    }
}
