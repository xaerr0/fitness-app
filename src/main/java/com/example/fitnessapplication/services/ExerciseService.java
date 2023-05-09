package com.example.fitnessapplication.services;

import com.example.fitnessapplication.dto.WorkoutRequest;
import com.example.fitnessapplication.models.BodyPart;
import com.example.fitnessapplication.models.Equipment;
import com.example.fitnessapplication.models.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ExerciseService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BodyPartService bodyPartService;

//    @Autowired
//    EquipmentService equipmentService;


    //    @Autowired
//    ExerciseRepo exerciseRepo;
    //    @Autowired
//    BodyGroupService bodyGroupService;
//    @Value("${upperBodyParts}")
//    List<String> upperBodyParts;

//    @PostConstruct
//    public void pullAndPersistExercises() {
//        if (exerciseRepo.findAll().size() == 0) {
//            List<Exercise> exerciseList = getAllExercises();
//            saveAllExercises(exerciseList);
//        }
//    }
//
//    private void saveAllExercises(List<Exercise> exerciseList) {
//        for (Exercise exercise : exerciseList) {
//            exerciseRepo.save(exercise);
//        }
//    }

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

    //Exercises by Multiple Bodyparts
    //TODO What's best naming practice between service and controller?
    public List<Exercise> getExercisesByMultipleBodyParts(List<String> bodyParts) {
        List<Exercise> exercises = new ArrayList<>();
        for (String bodyPart : bodyParts) {
            ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                    "https://exercisedb.p.rapidapi.com/exercises/bodyPart/" + bodyPart, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Exercise>>() {
                    });
            exercises.addAll(Objects.requireNonNull(response.getBody()));
        }
        return exercises;
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


    public List<Exercise> getByMultipleTargets(List<String> targetList) {
        List<Exercise> exercises = new ArrayList<>();
        for (String target : targetList) {
            ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                    "https://exercisedb.p.rapidapi.com/exercises/target/" + target, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Exercise>>() {
                    });
            exercises.addAll(Objects.requireNonNull(response.getBody()));
        }
        return exercises;
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

    public List<Exercise> getByMultipleEquipment(List<String> equipmentList) {
        List<Exercise> exercises = new ArrayList<>();
        for (String equipment : equipmentList) {
            ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                    "https://exercisedb.p.rapidapi.com/exercises/equipment/" + equipment, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<Exercise>>() {
                    });
            exercises.addAll(Objects.requireNonNull(response.getBody()));
        }
        return exercises;
    }

    //Filter by bodyGroups (upper body, lower body, cardio, abs)
    public List<Exercise> getExercises(WorkoutRequest workoutRequest) {
        List<BodyPart> bodyParts = bodyPartService.getAllBodyParts();
        List<Exercise> exerciseList = null;
        List<BodyPart> filteredBodyParts = bodyParts.stream()
                .filter(b -> b.getGroups().stream()
                        .anyMatch(group -> group.getName()
                                .equalsIgnoreCase(workoutRequest.getBodyGroup().getName())))
                .collect(Collectors.toList());

        List<String> bodyPartStrings = filteredBodyParts.stream()
                .map(BodyPart::getName)
                .collect(Collectors.toList());

        exerciseList = getExercisesByMultipleBodyParts(bodyPartStrings);

        //TODO Not 100% yet. logic works with even num of equipment
        List<Exercise> generatedExercises = new ArrayList<>();
        for (Equipment equipment : workoutRequest.getEquipment()) {
            Integer count = null;
            if (workoutRequest.getMinutes() == 30) {
                count = 6;
            } else if (workoutRequest.getMinutes() == 45) {
                count = 8;
            } else if (workoutRequest.getMinutes() == 60) {
                count = 10;
            }

            if (count != null) {
                generatedExercises.addAll(exerciseList.stream()
                        .filter(e -> e.getEquipment()
                                .equalsIgnoreCase(equipment.getName()))
                        .limit(count / workoutRequest.getEquipment().size())
                        .collect(Collectors.toList()));
            }
        }


        Collections.shuffle(generatedExercises);
        return generatedExercises;
    }

    //randomly pull from each body part twice
}