package com.example.fitnessapplication.controllers;

import com.example.fitnessapplication.models.BodyPart;
import com.example.fitnessapplication.models.Equipment;
import com.example.fitnessapplication.models.Target;
import com.example.fitnessapplication.services.BodyPartService;
import com.example.fitnessapplication.services.EquipmentService;
import com.example.fitnessapplication.services.TargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        return "generator";
    }
}