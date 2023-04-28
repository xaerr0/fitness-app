package com.example.fitnessapplication.controllers;

import com.example.fitnessapplication.models.*;
import com.example.fitnessapplication.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        List<Equipment> equipmentList = equipmentService.getAllEquipment();
        model.addAttribute("equipmentList", equipmentList);
        List<BodyPart> bodyPartList = bodypartService.getAllBodyParts();
        model.addAttribute("bodyPartList", bodyPartList);
        List<Target> targetList = targetService.getAllTargetMuscles();
        model.addAttribute("targetList", targetList);
        Map<String, List<String>> bodyGroupMap = bodyGroupService.getGroupMap();
        model.addAttribute("getGroupMap", bodyGroupMap);
        return "generator";
    }

    @ResponseBody
    @PostMapping("/generated")
    public String submitGenerator(@ModelAttribute("equipmentName") String equipmentName, Model model) {
        List<Exercise> exercise = exerciseService.getByEquipment(equipmentName);
        model.addAttribute("equipmentName", equipmentName);
        model.addAttribute("exercise", exercise);
        return "generated";
    }
}