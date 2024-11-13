package com.example.telt_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fuel_data")


public class FuelData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fuelDataId;

    @Column(name = "avl_data_id")
    private Long avlDataId;

    @Column(name = "fuel_level")
    private Short fuelLevel;

    @Column(name = "fuel_rate")
    private Float fuelRate;

    // Getters and Setters
    public Long getFuelDataId() { return fuelDataId; }
    public void setFuelDataId(Long fuelDataId) { this.fuelDataId = fuelDataId; }

    public Long getAvlDataId() { return avlDataId; }
    public void setAvlDataId(Long avlDataId) { this.avlDataId = avlDataId; }

    public Short getFuelLevel() { return fuelLevel; }
    public void setFuelLevel(Short fuelLevel) { this.fuelLevel = fuelLevel; }

    public Float getFuelRate() { return fuelRate; }
    public void setFuelRate(Float fuelRate) { this.fuelRate = fuelRate; }

}
