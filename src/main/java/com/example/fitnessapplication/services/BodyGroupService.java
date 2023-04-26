package com.example.fitnessapplication.services;


import com.example.fitnessapplication.models.Exercise;
import com.example.fitnessapplication.repos.ExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service

public class BodyGroupService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExerciseRepo exerciseRepo;

    @Autowired
    ExerciseService exerciseService;


    public List<String> getGroup(String group) {
        if (group.equalsIgnoreCase("upperBody")) {
            return List.of("chest", "back", "shoulders", "neck", "upper arms", "lower arms");
        }
        return null;
    }

    //TODO Build out the rest of the map
    public Map<String, List<String>> getGroupMap() {

        return Map.of("Upper Body", List.of("chest", "back", "shoulders", "neck", "upper arms", "lower arms"),
                "Lower Body", List.of("lower legs", "upper legs", "waist"));
    }

}