package com.example.fitnessapplication.controllers;

import com.example.fitnessapplication.models.BodyPart;
import com.example.fitnessapplication.services.BodyPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bodyparts")
public class BodyPartController {

    @Autowired
    BodyPartService bodypartService;

    @ResponseBody
    @GetMapping
    public List<BodyPart> getAllBodyParts() {
        return bodypartService.getAllBodyParts();
    }
}