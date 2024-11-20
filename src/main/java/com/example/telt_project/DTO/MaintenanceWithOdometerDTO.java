package com.example.telt_project.DTO;

public class MaintenanceWithOdometerDTO {
    private Long maintenanceId;
    private String operationType;
    private Long vehicleId;
    private Integer odometer; // from fuel_logs
    private Double price;
    private String alert; // optional, can still be set in the backend

    public MaintenanceWithOdometerDTO(Long maintenanceId, String operationType, Long vehicleId, Integer odometer, Double price, String alert) {
        this.maintenanceId = maintenanceId;
        this.operationType = operationType;
        this.vehicleId = vehicleId;
        this.odometer = odometer;
        this.price = price;
        this.alert = alert;
    }

    // Getters and setters
    public Long getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Long maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }
}
