package com.example.fitnessapplication.repos;

import com.example.fitnessapplication.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Long> {
    List<Exercise> findAll();
//    List<Exercise> getUpperBodyExercises();
//    List<Exercise> findTop10ExercisesByEquipment(String equipment);


}