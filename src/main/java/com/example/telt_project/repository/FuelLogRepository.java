package com.example.telt_project.repository;

import com.example.telt_project.model.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.telt_project.DTO.FuelLogWithVehicleDto; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuelLogRepository extends JpaRepository<FuelLog, Long> {

    List<FuelLog> findByVehicleId(Long vehicleId);
    FuelLog findTopByVehicleIdOrderByDateDesc(Long vehicleId);

    @Query("SELECT new com.example.telt_project.DTO.FuelLogWithVehicleDto(" +
            "fl.date, fl.fuelAmount, fl.fuelCost, fl.location, fl.odometer, fl.paymentMethod, v.plateNumber, v.make) " +
            "FROM FuelLog fl JOIN Vehicles v ON fl.vehicleId = v.vehicleId")
    List<FuelLogWithVehicleDto> findAllFuelLogsWithVehicle();

    @Query("SELECT fl.odometer FROM FuelLog fl WHERE fl.vehicleId = :vehicleId ORDER BY fl.date DESC LIMIT 1")
Optional<Long> findLatestOdometerByVehicleId(@Param("vehicleId") Long vehicleId);

}
