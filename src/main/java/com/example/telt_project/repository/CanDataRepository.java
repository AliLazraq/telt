package com.example.telt_project.repository;

import com.example.telt_project.model.CanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanDataRepository extends JpaRepository<CanData, Long> {
}
