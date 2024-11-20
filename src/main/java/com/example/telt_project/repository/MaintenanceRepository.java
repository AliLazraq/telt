package com.example.telt_project.repository;

import com.example.telt_project.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.telt_project.DTO.MaintenanceWithOdometerDTO;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByVehicleId(Long vehicleId);

//     @Query("SELECT new com.example.telt_project.DTO.MaintenanceWithOdometerDTO( " +
//        "m.id, m.operationType, m.vehicleId, MAX(fl.odometer), m.price, m.alert) " +
//        "FROM Maintenance m " +
//        "LEFT JOIN FuelLog fl ON m.vehicleId = fl.vehicleId " +
//        "WHERE m.vehicleId = :vehicleId " +
//        "GROUP BY m.id, m.operationType, m.vehicleId, m.price, m.alert")
// List<MaintenanceWithOdometerDTO> findMaintenanceWithOdometerByVehicleId(@Param("vehicleId") Long vehicleId);



}
