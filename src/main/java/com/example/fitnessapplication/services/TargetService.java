package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Target;
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

@Service
public class TargetService {

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    TargetRepo targetRepo;

//    @PostConstruct
//    public void pullAndPersistTargetMuscles() {
//        if (targetRepo.findAll().size() == 0) {
//            List<Target> targetList = getAllTargetMuscles();
//            saveAllTargets(targetList);
//        }
//    }
//
//    private void saveAllTargets(List<Target> targetList) {
//        for (Target target : targetList) {
//            targetRepo.save(target);
//        }
//    }

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