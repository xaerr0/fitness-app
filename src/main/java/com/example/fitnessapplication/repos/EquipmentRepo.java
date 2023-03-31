package com.example.fitnessapplication.repos;

import com.example.fitnessapplication.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment, Long> {

}