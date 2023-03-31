package com.example.fitnessapplication.repos;

import com.example.fitnessapplication.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
    List<Exercise> findAll();


}