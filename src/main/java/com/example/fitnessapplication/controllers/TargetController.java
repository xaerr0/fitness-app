package com.example.fitnessapplication.controllers;


import com.example.fitnessapplication.models.Target;
import com.example.fitnessapplication.services.TargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/target")
public class TargetController {

    @Autowired
    TargetService targetService;

    @GetMapping
    public List<Target> getAllTargetMuscles() {
        return targetService.getAllTargetMuscles();
    }
}