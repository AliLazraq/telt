package com.example.telt_project.repository;

import com.example.telt_project.model.avlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public interface avlDataRepository extends JpaRepository<avlData, Long> {
    @Query("SELECT a.latitude, a.longitude, a.speed, a.timestamp FROM avlData a")
    List<Object[]> findGpsData();

    avlData findTopByDeviceIdOrderByTimestampDesc(Long deviceId);

    @Query(value = """
        SELECT 
            vehicles.vehicle_id AS id,
            vehicles.plate_number,
            vehicles.make,
            vehicles.model,
            avl_data.latitude,
            avl_data.longitude,
            avl_data.angle,
            avl_data.speed,
            avl_data.timestamp AS timestamp
        FROM 
            vehicles
        JOIN 
            devices ON vehicles.vehicle_id = devices.vehicle_id
        JOIN 
            (SELECT device_id, MAX(timestamp) AS latest_timestamp
             FROM avl_data
             GROUP BY device_id) AS latest_avl ON devices.device_id = latest_avl.device_id
        JOIN 
            avl_data ON devices.device_id = avl_data.device_id 
                     AND avl_data.timestamp = latest_avl.latest_timestamp;
    """, nativeQuery = true)
    List<Map<String, Object>> getLatestGpsData();
}