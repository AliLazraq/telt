package com.example.telt_project.repository;

import com.example.telt_project.model.ioData;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ioDataRepository extends JpaRepository<ioData, Long> {
}