package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Exercise;
import com.example.fitnessapplication.repos.ExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Component

public class ExerciseService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ExerciseRepo exerciseRepo;

    @Autowired
    BodyGroupService bodyGroupService;

    @Value("${upperBodyParts}")
    List<String> upperBodyParts;


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

//TODO Workin Progress
    public List<Exercise> getByBodyPartUpper(List<String> bodyPart) {

        for (String s : bodyPart) {

            URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/bodyPart/" + s)
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
        return null;
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





//    public List<Exercise> getTop10ExercisesByEquipment(String equipment) {
//        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/equipment/" + equipment)
//                .build()
//                .toUri();
//
//
//
//        ResponseEntity<List<Exercise>> response =
//                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {
//                });
//        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
//            return response.getBody();
//        } else {
//            return null;
//        }
//    }
}