package com.example.fitnessapplication.controllers;

import com.example.fitnessapplication.models.Exercise;
import com.example.fitnessapplication.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
@Component
public class ExerciseController {


    @Autowired
    ExerciseService exerciseService;


    @GetMapping()
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    // http://localhost:8080/exercises/exercise?id=7
    @GetMapping(value = "/exercise", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exercise getExercise(@RequestParam Long id) {
        return exerciseService.getExercise(id);
    }


    //http://localhost:8080/exercises/name?name=back
    @GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Exercise> getExercisesByName(@RequestParam String name) {
        return exerciseService.getExerciseByName(name);
    }

    //http://localhost:8080/exercises/bodypart?bodypart=chest
    @GetMapping(value = "/bodypart", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Exercise> getExerciseByBodyPart(@RequestParam String bodypart) {
        return exerciseService.getByBodyPart(bodypart);
    }

    @GetMapping("/upper")
    public List<Exercise> getExerciseByBodyPartUpper(@RequestBody List<String> bodyPart) {

        return exerciseService.getByBodyPartUpper(bodyPart);
    }

    //http://localhost:8080/exercises/equipment?equipment=barbell
    @GetMapping(value = "/equipment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Exercise> getExerciseByEquipment(@RequestParam String equipment) {
        return exerciseService.getByEquipment(equipment);
    }

    //http://localhost:8080/exercises/target?target=calves
    @GetMapping(value = "/target", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Exercise> getExerciseByTarget(@RequestParam String target) {
        return exerciseService.getByTargetMuscles(target);
    }

    //http://localhost:8080/exercises/type?upperbody=chest,back,waist
    @GetMapping(value = "/type", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Exercise> getExerciseByMultipleBodyParts(@RequestParam("upperbody") List<String> upperbody) {
        return exerciseService.getExercisesByMultipleBodyParts(upperbody);
    }

//    @GetMapping("/top10/equipment/{equipment}")
//    public List<Exercise> getTop10Exercises(@PathVariable String equipment) {
//        return exerciseService.getTop10ExercisesByEquipment(equipment);
//    }
}