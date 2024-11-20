package com.example.telt_project.service;

import com.example.telt_project.DTO.FuelLogWithVehicleDto;
import com.example.telt_project.DTO.MaintenanceWithOdometerDTO;
import com.example.telt_project.model.*;
import com.example.telt_project.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeltonikaDataService {

    // Repository Declarations
    private final avlDataRepository avlDataRepository;
    private final CanDataRepository canDataRepository;
    private final DeviceRepository deviceRepository;
    private final FuelDataRepository fuelDataRepository;
    private final VehiclesRepository vehiclesRepository;
    private final ioDataRepository ioDataRepository;
    private final FuelLogRepository fuelLogRepository;
    private final CityGeofencingRepository cityGeofencingRepository;
    private final AlertRepository alertRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final TrackerRepository TrackerRepository;
    private static final double EARTH_RADIUS = 6371000;

    // Constructor Injection of Repositories
    @Autowired
    public TeltonikaDataService(
            avlDataRepository avlDataRepository,
            CanDataRepository canDataRepository,
            DeviceRepository deviceRepository,
            FuelDataRepository fuelDataRepository,
            VehiclesRepository vehiclesRepository,
            ioDataRepository ioDataRepository,
            FuelLogRepository fuelLogRepository,
            CityGeofencingRepository cityGeofencingRepository,
            MaintenanceRepository maintenanceRepository,
            TrackerRepository TrackerRepository,
            AlertRepository alertRepository) {

        this.avlDataRepository = avlDataRepository;
        this.canDataRepository = canDataRepository;
        this.deviceRepository = deviceRepository;
        this.fuelDataRepository = fuelDataRepository;
        this.vehiclesRepository = vehiclesRepository;
        this.ioDataRepository = ioDataRepository;
        this.fuelLogRepository = fuelLogRepository;
        this.cityGeofencingRepository = cityGeofencingRepository;
        this.alertRepository = alertRepository;
        this.maintenanceRepository = maintenanceRepository;
        this.TrackerRepository = TrackerRepository;
    }

    // ===================== avl_data (VehicleData) Methods =====================
    public List<avlData> getAllAvlData() {
        return avlDataRepository.findAll();
    }

    public Optional<avlData> getDataById(Long id) {
        return avlDataRepository.findById(id);
    }

    public avlData saveData(avlData vehicleData) {
        return avlDataRepository.save(vehicleData);
    }

    public void deleteData(Long id) {
        avlDataRepository.deleteById(id);
    }

    // ============================= GPS =============================
    public List<Map<String, Object>> fetchLatestGpsData() {
        return avlDataRepository.getLatestGpsData();
    }

    public List<Map<String, Object>> getGpsData() {
        return avlDataRepository.findGpsData().stream()
                .map(row -> Map.of(
                        "latitude", row[0],
                        "longitude", row[1],
                        "speed", row[2],
                        "timestamp", row[3]))
                .collect(Collectors.toList());
    }

    // ===================== can_data Methods =====================
    public List<CanData> getAllCanData() {
        return canDataRepository.findAll();
    }

    public Optional<CanData> getCanDataById(Long id) {
        return canDataRepository.findById(id);
    }

    public CanData saveCanData(CanData canData) {
        return canDataRepository.save(canData);
    }

    public void deleteCanData(Long id) {
        canDataRepository.deleteById(id);
    }

    // ===================== Device Methods =====================
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(int id) {
        return deviceRepository.findById(id);
    }

    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    public void deleteDevice(int id) {
        deviceRepository.deleteById(id);
    }

    public long countByIsActive(int isActive) {
        return deviceRepository.countByIsActive(isActive);
    }

    // ===================== fuel_data Methods =====================
    public List<FuelData> getAllFuelData() {
        return fuelDataRepository.findAll();
    }

    public Optional<FuelData> getFuelDataById(Long id) {
        return fuelDataRepository.findById(id);
    }

    public FuelData saveFuelData(FuelData fuelData) {
        return fuelDataRepository.save(fuelData);
    }

    public void deleteFuelData(Long id) {
        fuelDataRepository.deleteById(id);
    }

    // ===================== vehicles Methods =====================
    public List<Vehicles> getAllVehicles() {
        return vehiclesRepository.findAll();
    }

    public Optional<Vehicles> getVehicleById(Long id) {
        return vehiclesRepository.findById(id);
    }

    public Vehicles saveVehicle(Vehicles vehicle) {
        return vehiclesRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehiclesRepository.deleteById(id);
    }

    public long getVehicleCount() {
        return vehiclesRepository.countVehicles();
    }

    // ===================== io_data Methods =====================
    public List<ioData> getAllioData() {
        return ioDataRepository.findAll();
    }

    public Optional<ioData> getioDataById(Long id) {
        return ioDataRepository.findById(id);
    }

    public ioData saveioData(ioData ioData) {
        return ioDataRepository.save(ioData);
    }

    public void deleteioData(Long id) {
        ioDataRepository.deleteById(id);
    }

    public void deleteAllioData() {
        ioDataRepository.deleteAll();
    }

    // ===================== fuel_log Methods =====================
    public List<FuelLog> getAllFuelLogs() {
        return fuelLogRepository.findAll();
    }

    public FuelLog saveFuelLog(FuelLog fuelLog) {
        //update trackers
        updateTrackers(fuelLog.getVehicleId(), fuelLog.getOdometer());
        return fuelLogRepository.save(fuelLog);
    }

    

    public void updateTrackers(Long vehicleId, Integer odometer) {
        // Fetch the latest tracker data for the vehicle
        List<Tracker> trackers = TrackerRepository.findByVehicleId(vehicleId);
        if (trackers == null) {
            throw new RuntimeException("No tracker data found for vehicle ID " + vehicleId);
        }

        List<FuelLog> fuelLogs = fuelLogRepository.findByVehicleId(vehicleId);
        FuelLog latestFuelLog = fuelLogs.stream()
            .max((log1, log2) -> log1.getDate().compareTo(log2.getDate()))
            .orElseThrow(() -> new RuntimeException("No fuel logs found for vehicle ID " + vehicleId));
        Integer oldOdometer = latestFuelLog.getOdometer();
        Integer distance = odometer - oldOdometer;

        for (Tracker tracker : trackers) {
            tracker.setValue(tracker.getValue() - distance);
            TrackerRepository.save(tracker);
        }
    }


    public void resetTrackers(Long vehicleId, String operationType) {
        // Fetch the latest tracker data for the vehicle
        List<Tracker> trackers = TrackerRepository.findByVehicleId(vehicleId);
        if (trackers == null) {
            throw new RuntimeException("No tracker data found for vehicle ID " + vehicleId);
        }

        Map<String, Long> thresholds = Map.of(
            "Oil Change", 10000L,
            "Tires Change", 50000L,
            "Distribution Chain Change", 150000L
        );

        System.out.println("i ama here");

        // Reset only the specific operation type
        for (Tracker tracker : trackers) {
            if (tracker.getOperationType().equals(operationType)) {
                if (thresholds.containsKey(operationType)) {
                    tracker.setValue(thresholds.get(operationType));
                } else {
                    tracker.setValue(0L);
                }
                TrackerRepository.save(tracker);
            }
        }
   
    }

    public List<FuelLog> getFuelLogsByVehicleId(Long vehicleId) {
        return fuelLogRepository.findByVehicleId(vehicleId);
    }

    public Optional<FuelLog> getFuelLogById(Long fuelLogId) {
        return fuelLogRepository.findById(fuelLogId);
    }

    public FuelLog updateFuelLog(Long fuelLogId, FuelLog updatedFuelLog) {
        return fuelLogRepository.findById(fuelLogId).map(fuelLog -> {
            fuelLog.setFuelAmount(updatedFuelLog.getFuelAmount());
            fuelLog.setFuelCost(updatedFuelLog.getFuelCost());
            fuelLog.setLocation(updatedFuelLog.getLocation());
            fuelLog.setOdometer(updatedFuelLog.getOdometer());
            return fuelLogRepository.save(fuelLog);
        }).orElseThrow(() -> new RuntimeException("Fuel Log not found with id " + fuelLogId));
    }

    public void deleteFuelLog(Long fuelLogId) {
        fuelLogRepository.deleteById(fuelLogId);
    }

    public FuelLog getLatestOdometerByVehicleId(Long vehicleId) {
        return fuelLogRepository.findTopByVehicleIdOrderByDateDesc(vehicleId);
    }

    public void deleteAllFuelLogs() {
        fuelLogRepository.deleteAll();
    }

    public List<FuelLogWithVehicleDto> getAllFuelLogsWithVehicle() {
        // Fetch all joined fuel log and vehicle data
        return fuelLogRepository.findAllFuelLogsWithVehicle();
    }

    // ===================== city_geofence Methods =====================
    public List<CityGeofencing> getAllGeofences() {
        return cityGeofencingRepository.findAll();
    }

    public Optional<CityGeofencing> getGeofenceById(Long id) {
        return cityGeofencingRepository.findById(id);
    }

    public CityGeofencing saveGeofence(CityGeofencing geofence) {
        return cityGeofencingRepository.save(geofence);
    }

    public void deleteGeofence(Long id) {
        cityGeofencingRepository.deleteById(id);
    }

    public void deleteAllGeofences() {
        cityGeofencingRepository.deleteAll();
    }

    // ===================== alert Methods =====================
    public List<Alert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public Optional<Alert> getAlertById(Long id) {
        return alertRepository.findById(id);
    }

    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }

    // get all avl data for a device
    public List<avlData> getAvlDataByDeviceId(Long deviceId) {
        return avlDataRepository.findByDeviceId(deviceId);
    }

    // Check if a device breaches a city geofence
    public void checkCityGeofenceBreach(Long deviceId, String cityName) {
        // Fetch city geofence details
        Optional<CityGeofencing> geofence = cityGeofencingRepository.findByName(cityName);
        if (geofence.isEmpty()) {
            throw new RuntimeException("City geofence not found for " + cityName);
        }

        // Fetch latest AVL data for the device
        var latestData = avlDataRepository.findTopByDeviceIdOrderByTimestampDesc(deviceId);
        if (latestData == null) {
            throw new RuntimeException("No AVL data found for device ID " + deviceId);
        }

        // Check if the device is outside the geofence
        CityGeofencing cityGeofence = geofence.get();
        double distance = calculateDistance(
                latestData.getLatitude(),
                latestData.getLongitude(),
                cityGeofence.getCenterLatitude(),
                cityGeofence.getCenterLongitude());

        if (distance > cityGeofence.getRadius()) {
            // Log breach in the alerts table
            Alert alert = new Alert();
            alert.setVehicleId(deviceId);
            alert.setGeofenceId(cityGeofence.getGeofenceId());
            alert.setLatitude(latestData.getLatitude());
            alert.setLongitude(latestData.getLongitude());
            alert.setTimestamp(latestData.getTimestamp());
            alert.setBreachStatus("BREACH"); // Explicitly set the breach status
            alertRepository.save(alert);
        }
    }

    // Calculate distance using Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double EARTH_RADIUS = 6371000; // Earth radius in meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    // ===================== maintenance Methods =====================

    public Maintenance saveMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }

    public List<Maintenance> getAllMaintenance() {
        return maintenanceRepository.findAll();
    }

    public List<Maintenance> getMaintenanceByVehicleId(Long vehicleId) {
        return maintenanceRepository.findByVehicleId(vehicleId);
    }

    public void deleteMaintenance(Long maintenanceId) {
        maintenanceRepository.deleteById(maintenanceId);
    }

    public Maintenance saveMaintenance(Long vehicleId, Maintenance maintenance) {
        // reset trackers
        resetTrackers(vehicleId, maintenance.getOperationType());
        return maintenanceRepository.save(maintenance);
    }

    public Map<String, Object> getMaintenanceAlertsWithOdometer(Long vehicleId) {

        System.out.println("Vehicle ID: " + vehicleId);
        // Fetch the latest odometer value from fuel_logs
        Long currentOdometer = fuelLogRepository.findLatestOdometerByVehicleId(vehicleId)
                .orElse(0L); // Default to 0 if no logs exist

        List<Maintenance> maintenanceList = maintenanceRepository.findByVehicleId(vehicleId);

        // Thresholds for maintenance
        Map<String, Long> thresholds = new HashMap<>();
        thresholds.put("Oil Change", 10000L);
        thresholds.put("Tyre Change", 50000L);
        thresholds.put("Distribution Change", 150000L);

        // Generate alerts and update thresholds dynamically
        for (Maintenance maintenance : maintenanceList) {
            String operationType = maintenance.getOperationType();
            String alert = "No Alert";

            if (operationType != null && thresholds.containsKey(operationType)) {
                Long threshold = thresholds.get(operationType);
                Long nextThreshold = maintenance.getMaintenanceDate() != null
                        ? currentOdometer + threshold // Set the next threshold
                        : threshold;

                if (currentOdometer >= nextThreshold) {
                    alert = operationType + " Needed!";
                }

                // Update maintenance alert and calculate remaining km
                maintenance.setAlert(alert);
            }
        }

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("currentOdometer", currentOdometer);
        response.put("maintenanceList", maintenanceList);

        return response;
    }

    // public List<MaintenanceWithOdometerDTO> getMaintenanceWithOdometer(Long
    // vehicleId) {
    // return
    // maintenanceRepository.findMaintenanceWithOdometerByVehicleId(vehicleId);
    // }

    // The method save(Tracker)

    public Tracker save(Tracker tracker) {
        return TrackerRepository.save(tracker);
    }

    public void deleteAll() {
        TrackerRepository.deleteAll();
    }

    public List<Tracker> getAllTrackers() {
        return TrackerRepository.findAll();
    }

    public void deleteTracker(Long id) {
        TrackerRepository.deleteById(id);
    }

    public List<Tracker> getTrackerByVehicleId(Long vehicleId) {
        return TrackerRepository.findByVehicleId(vehicleId);
    }
}