package com.example.telt_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class Device {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long deviceId;
    
        @Column(name = "imei")
        private String deviceImei;

        @Column(name = "vehicle_id")
        private Long deviceVehicleId;
    
        @Column(name = "is_active")
        private Integer isActive;
    
        // Getters and Setters
        public Long getDeviceId() { return deviceId; }
        public void setDeviceId(Long deviceId) { this.deviceId = deviceId; }

        public String getDeviceImei() { return deviceImei; }
        public void setDeviceImei(String deviceImei) { this.deviceImei = deviceImei; }

        public Long getDeviceVehicleId() { return deviceVehicleId; }
        public void setDeviceVehicleId(Long deviceVehicleId) { this.deviceVehicleId = deviceVehicleId; }

        public Integer getIsActive() { return isActive; }
        public void setIsActive(Integer isActive) { this.isActive = isActive; }
        
    }