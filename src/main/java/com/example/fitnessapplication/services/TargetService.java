package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Bodypart;
import com.example.fitnessapplication.models.Target;
import com.example.fitnessapplication.repos.TargetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TargetService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TargetRepo targetRepo;

    @Value("${api_key}")
    private String apiKey;

    @Value("${api_host}")
    private String apiHost;


    @PostConstruct
    public void pullAndPersistTargetMuscles() {
        if (targetRepo.findAll().size() == 0) {
            List<Target> targetList = getAllTargetMuscles();
            saveAllTargets(targetList);
        }
    }

    private void saveAllTargets(List<Target> targetList) {
        for (Target target : targetList) {
            targetRepo.save(target);
        }
    }

    //    List All Target Muscles
    public List<Target> getAllTargetMuscles() {

        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/targetList")
                .build()
                .toUri();

        ResponseEntity<List<Target>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Target>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }
}