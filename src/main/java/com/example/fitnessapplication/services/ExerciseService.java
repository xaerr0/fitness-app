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

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ExerciseService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExerciseRepo exerciseRepo;


    @PostConstruct
    public void pullAndPersistExercises() {
        if (exerciseRepo.findAll().size() == 0) {
            List<Exercise> exerciseList = getAllExercises();
            saveAllExercises(exerciseList);
        }
    }

    private void saveAllExercises(List<Exercise> exerciseList) {
        for (Exercise exercise : exerciseList) {
            exerciseRepo.save(exercise);
        }
    }

    public List<Exercise> getAllExercises() {
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises")
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public Exercise getExercise(Long id) {
        // id is 4 digits i.e. 5 = 0005, 43 = 0043
        String paddedId = String.format("%04d", id);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/exercise/" + paddedId)
                .build()
                .toUri();

        ResponseEntity<Exercise> response = restTemplate.exchange(uri, HttpMethod.GET, null, Exercise.class);
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public List<Exercise> getExerciseByName(String name) {
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/name/" + name)
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {
                });
        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public List<Exercise> getByBodyPart(String bodyPart) {
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/bodyPart/" + bodyPart)
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //Exercise by Target Muscle
    public List<Exercise> getByTargetMuscles(String target) {
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/target/" + target)
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //Exercise By Equipment
    public List<Exercise> getByEquipment(String equipment) {
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/equipment/" + equipment)
                .build()
                .toUri();

        ResponseEntity<List<Exercise>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //TODO don't think this is correct
    public List<Exercise> getUpperBodyExercises(List<String> upperBodyParts) {
        List<Exercise> exercises = new ArrayList<>();
        for (String bodyPart : upperBodyParts) {
            ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                    "https://exercisedb.p.rapidapi.com/exercises/bodypart/" + bodyPart, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Exercise>>() {
            });
//if all good
            exercises.addAll(response.getBody());
        }

        return exercises;
    }
}