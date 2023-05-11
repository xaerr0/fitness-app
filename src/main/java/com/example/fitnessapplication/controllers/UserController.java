package com.example.fitnessapplication.controllers;

import com.example.fitnessapplication.dto.WorkoutRequest;
import com.example.fitnessapplication.models.*;
import com.example.fitnessapplication.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    BodyPartService bodypartService;

    @Autowired
    TargetService targetService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    BodyGroupService bodyGroupService;


    @GetMapping("/register")
    public String login() {
        return "/register";
    }

    @GetMapping("/generator")
    public String generatorPage(Model model) {
        List<Equipment> equipmentList = equipmentService.getFilteredEquipment();
        model.addAttribute("equipmentList", equipmentList);
        List<BodyPart> bodyPartList = bodypartService.getAllBodyParts();
        model.addAttribute("bodyPartList", bodyPartList);
        List<Target> targetList = targetService.getAllTargetMuscles();
        model.addAttribute("targetList", targetList);
        List<BodyGroup> bodyGroupList = bodyGroupService.getAllBodyGroups();
        model.addAttribute("bodyGroupList", bodyGroupList);
        WorkoutRequest workoutRequest = new WorkoutRequest();
        model.addAttribute("workoutRequest", workoutRequest);

        return "generator";
    }

    @PostMapping("/generated")
    public String submitGenerator(@ModelAttribute("workoutRequest") WorkoutRequest workoutRequest, Model model) {
        List<Exercise> exerciseRequest = exerciseService.getExercises(workoutRequest);
        model.addAttribute("exerciseRequest", exerciseRequest);
        return "generated";
    }
}