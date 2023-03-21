package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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


    public List<Exercise> getAllExercises() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises")
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Exercise>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //TODO Fix this
    public Exercise getExercise(Long id) {
//        String paddedId = String.format("%04d", id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/exercise/" + id)
                .build()
                .toUri();

        ResponseEntity<Exercise> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Exercise.class);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public List<Exercise> getExerciseByName(String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/name/" + name)
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Exercise>>() {
                });
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public List<Exercise> getByBodyPart(String bodyPart) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/bodyPart/" + bodyPart)
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Exercise>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //Exercise by Target Muscle
    public List<Exercise> getByTargetMuscles(String target) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/target/" + target)
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Exercise>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //Exercise By Equipment
    public List<Exercise> getByEquipment(String equipment) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/equipment/" + equipment)
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Exercise>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }
}