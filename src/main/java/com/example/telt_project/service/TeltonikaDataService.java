package com.example.telt_project.service;

import com.example.telt_project.model.avlData;
import com.example.telt_project.model.CanData;
import com.example.telt_project.model.Device;
import com.example.telt_project.model.FuelData;
import com.example.telt_project.model.Vehicles;
import com.example.telt_project.model.ioData;
import com.example.telt_project.repository.avlDataRepository;
import com.example.telt_project.repository.CanDataRepository;
import com.example.telt_project.repository.DeviceRepository;
import com.example.telt_project.repository.FuelDataRepository;
import com.example.telt_project.repository.VehiclesRepository;
import com.example.telt_project.repository.ioDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeltonikaDataService {

    private final avlDataRepository avlDataRepository;
    private final CanDataRepository canDataRepository; 
    private final DeviceRepository deviceRepository;
    public final FuelDataRepository fuelDataRepository;
    public final VehiclesRepository vehiclesRepository;
    public final ioDataRepository ioDataRepository;

    @Autowired
    public TeltonikaDataService(avlDataRepository vehicleDataRepository, CanDataRepository canDataRepository, DeviceRepository deviceRepository, FuelDataRepository fuelDataRepository, VehiclesRepository vehiclesRepository, ioDataRepository ioDataRepository) {
        this.avlDataRepository = vehicleDataRepository;
        this.canDataRepository = canDataRepository; 
        this.deviceRepository = deviceRepository;
        this.fuelDataRepository = fuelDataRepository;
        this.vehiclesRepository = vehiclesRepository;
        this.ioDataRepository = ioDataRepository;
    }

    // Methods for avl_data (VehicleData) table
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

     public List<Map<String, Object>> getGpsData() {
        return avlDataRepository.findGpsData().stream().map(row -> {
            Map<String, Object> gpsData = Map.of(
                "latitude", row[0],
                "longitude", row[1],
                "speed", row[2],
                "timestamp", row[3]
            );
            return gpsData;
        }).collect(Collectors.toList());
    }

    // Methods for can_data (CanData) table
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

    // Methods for device table
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

    // Methods for fuel_data table
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

    // Methods for vehicles table
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

    // Methods for io_data table
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
}
