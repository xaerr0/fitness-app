package com.example.fitnessapplication.repos;

import com.example.fitnessapplication.models.Target;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepo extends JpaRepository<Target, Long> {

}