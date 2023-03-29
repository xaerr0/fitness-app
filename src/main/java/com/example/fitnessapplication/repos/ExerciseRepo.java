package com.example.fitnessapplication.repos;

import com.example.fitnessapplication.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
    Exercise findByName(String name);

    Exercise findById(long id);

    void deleteById(long id);

    void deleteByName(String name);

    void deleteAll();

    void deleteAllByName(String name);

    void deleteAllById(long id);

    void deleteAllByExerciseId(long exerciseId);

    void deleteAllByExerciseName(String exerciseName);
}