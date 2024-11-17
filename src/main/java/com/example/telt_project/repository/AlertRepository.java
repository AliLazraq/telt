package com.example.telt_project.repository;

import com.example.telt_project.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {}
