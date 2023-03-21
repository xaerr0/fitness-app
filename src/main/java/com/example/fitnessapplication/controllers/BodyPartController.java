package com.example.fitnessapplication.controllers;

import com.example.fitnessapplication.models.Bodypart;
import com.example.fitnessapplication.services.BodypartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/bodyparts")
public class BodyPartController {

    @Autowired
    BodypartService bodypartService;

    @ResponseBody
    @GetMapping
    public List<Bodypart> getAllBodyParts() {
        return bodypartService.getAllBodyParts();
    }
}