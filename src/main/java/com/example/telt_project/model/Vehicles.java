package com.example.telt_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicles {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Long year;

    @Column(name = "vin")
    private String vin;

    // Getters and Setters
    public Long getVehicleId() { return vehicleId; }
    public void setVehicleId(Long vehicleId) { this.vehicleId = vehicleId; }

    public String getPlateNumber() { return plateNumber; }
    public void setPlateNumber(String plateNumber) { this.plateNumber = plateNumber; }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public Long getYear() { return year; }
    public void setYear(Long year) { this.year = year; }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }
    
}