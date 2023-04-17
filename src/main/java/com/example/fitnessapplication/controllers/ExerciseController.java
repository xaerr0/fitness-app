package com.example.fitnessapplication.controllers;

import com.example.fitnessapplication.models.Exercise;
import com.example.fitnessapplication.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {


    @Autowired
    ExerciseService exerciseService;


    @GetMapping()
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    @GetMapping("/exercise/{id}")
    public Exercise getExercise(@PathVariable Long id) {
        return exerciseService.getExercise(id);
    }


    @GetMapping("/name/{name}")
    public List<Exercise> getExercisesByName(@PathVariable String name) {
        return exerciseService.getExerciseByName(name);
    }


    @GetMapping("/bodypart/{bodyPart}")
    public List<Exercise> getExerciseByBodyPart(@PathVariable String bodyPart) {
        return exerciseService.getByBodyPart(bodyPart);
    }


    @GetMapping("/equipment/{equipment}")
    public List<Exercise> getExerciseByEquipment(@PathVariable String equipment) {
        return exerciseService.getByEquipment(equipment);
    }


    @GetMapping("/target/{target}")
    public List<Exercise> getExerciseByTarget(@PathVariable String target) {
        return exerciseService.getByTargetMuscles(target);
    }

    @ResponseBody
    @GetMapping("/type/upperbody")
    public List<Exercise> getUpperBodyExercises(List<String> upperBody) {
        return exerciseService.getUpperBodyExercises(upperBody);
    }

    @GetMapping("/top10/equipment/{equipment}")
    public List<Exercise> getTop10Exercises(@PathVariable String equipment) {
        return exerciseService.getTop10ExercisesByEquipment(equipment);
    }
}