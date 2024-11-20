package com.example.telt_project.DTO;

import java.time.LocalDateTime;

public class FuelLogWithVehicleDto {
    private LocalDateTime date;
    private double fuelAmount;
    private double fuelCost;
    private String location;
    private Integer odometer;
    private String paymentMethod;
    private String plateNumber;
    private String make;

    public FuelLogWithVehicleDto(LocalDateTime date, double fuelAmount, double fuelCost, String location,
                                 Integer odometer, String paymentMethod, String plateNumber, String make) {
        this.date = date;
        this.fuelAmount = fuelAmount;
        this.fuelCost = fuelCost;
        this.location = location;
        this.odometer = odometer;
        this.paymentMethod = paymentMethod;
        this.plateNumber = plateNumber;
        this.make = make;
    }

    // Getters and Setters
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
