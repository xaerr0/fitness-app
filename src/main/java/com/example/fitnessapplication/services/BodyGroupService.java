package com.example.fitnessapplication.services;


import com.example.fitnessapplication.models.BodyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BodyGroupService {

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    ExerciseRepo exerciseRepo;

    @Autowired
    ExerciseService exerciseService;


    //TODO Build out the rest of the map
//    public Map<String, List<String>> getGroupMap() {
//
//        return Map.of(
//                "Upper Body", List.of("chest", "back", "shoulders", "neck", "upper arms", "lower arms"),
//                "Lower Body", List.of("lower legs", "upper legs"),
//                "Full Body", List.of("chest", "back", "shoulders", "neck", "upper arms", "lower arms",
//                        "lower legs", "upper legs"),
//                "Cardio", Collections.singletonList("cardio"),
//
//                "Abs", Collections.singletonList("waist"));
//    }

    public List<BodyGroup> getAllBodyGroups() {
        return List.of(
                new BodyGroup ("Upper Body"),
                new BodyGroup ("Lower Body"),
                new BodyGroup ("Full Body"),
                new BodyGroup ("Abs"),
                new BodyGroup ("Cardio"));
    }
}