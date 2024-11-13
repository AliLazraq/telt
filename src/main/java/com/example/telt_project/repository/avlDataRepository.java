package com.example.telt_project.repository;

import com.example.telt_project.model.avlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface avlDataRepository extends JpaRepository<avlData, Long> {
    @Query("SELECT a.latitude, a.longitude, a.speed, a.timestamp FROM avlData a")
    List<Object[]> findGpsData();
}