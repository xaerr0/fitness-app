package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.BodyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BodyGroupService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExerciseService exerciseService;


    public List<BodyGroup> getAllBodyGroups() {
        return List.of(
                new BodyGroup("Upper Body"),
                new BodyGroup("Lower Body"),
                new BodyGroup("Full Body"),
                new BodyGroup("Abs"),
                new BodyGroup("Cardio"));
    }
}