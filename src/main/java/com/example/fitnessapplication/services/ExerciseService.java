package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Exercise;
import com.example.fitnessapplication.models.ExerciseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api_key}")
    private String apiKey;

    @Value("${api_host}")
    private String apiHost;

    @Value("${exerciseListUrl}")
    private String exerciseListUrl;


    public Exercise[] getAllExercises() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises")
                .build()
                .toUri();

        ResponseEntity<Exercise[]> response =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Exercise[].class);


        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        } else {
            return null;
        }
    }
}