package com.example.fitnessapplication.controllers;

import com.example.fitnessapplication.models.Exercise;
import com.example.fitnessapplication.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {


    @Autowired
    ExerciseService exerciseService;

    @ResponseBody
    @GetMapping()
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }
    @ResponseBody
    @GetMapping("/{id}")
    public Exercise getExercise(@PathVariable Long id) {
        return exerciseService.getExercise(id);
    }

    @ResponseBody
    @GetMapping("/name/{name}")
    public List<Exercise> getExercisesByName(@PathVariable String name) {
        return exerciseService.getExerciseByName(name);
    }

    @ResponseBody
    @GetMapping("/bodypart/{bodyPart}")
    public List<Exercise> getExerciseByBodyPart(@PathVariable String bodyPart) {
        return exerciseService.getByBodyPart(bodyPart);
    }

    @ResponseBody
    @GetMapping("/equipment/{equipment}")
    public List<Exercise> getExerciseByEquipment(@PathVariable String equipment) {
        return exerciseService.getByEquipment(equipment);
    }

    @ResponseBody
    @GetMapping("/target/{target}")
    public List<Exercise> getExerciseByTarget(@PathVariable String target) {
        return exerciseService.getByTargetMuscles(target);
    }
}