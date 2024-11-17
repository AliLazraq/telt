package com.example.telt_project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fuel_logs")
public class FuelLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelLogId;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @Column(nullable = false)
    private double fuelAmount;

    @Column(nullable = false)
    private double fuelCost;

    @Column
    private String location;

    @Column
    private Integer odometer;

    @Column
    private String paymentMethod;

    @Column(name = "date", nullable = false, updatable = false)
    private LocalDateTime date = LocalDateTime.now();

    // Getters and Setters
    public Long getFuelLogId() {
        return fuelLogId;
    }

    public void setFuelLogId(Long fuelLogId) {
        this.fuelLogId = fuelLogId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public double getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(double fuelCost) {
        this.fuelCost = fuelCost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
