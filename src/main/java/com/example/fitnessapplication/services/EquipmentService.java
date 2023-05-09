package com.example.fitnessapplication.services;


import com.example.fitnessapplication.models.Equipment;
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
public class EquipmentService {

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    EquipmentRepo equipmentRepo;

//    @PostConstruct
//    public void pullAndPersistEquipment() {
//        if (equipmentRepo.findAll().size() == 0) {
//            List<Equipment> equipmentList = getAllEquipment();
//            saveAllEquipment(equipmentList);
//        }
//    }
//
//    private void saveAllEquipment(List<Equipment> equipmentList) {
//        for (Equipment equipment : equipmentList) {
//            equipmentRepo.save(equipment);
//        }
//    }

    //List All Equipment
    public List<Equipment> getAllEquipment() {
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/equipmentList")
                .build()
                .toUri();

        ResponseEntity<List<Equipment>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Equipment>>() {
                });


        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    //TODO Remove this from getExercises() method prior to calling getAllBodyParts()?
    public List<Equipment> getFilteredEquipment() {
        List<Equipment> filteredEquipment = getAllEquipment();

        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Assisted"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Elliptical Machine"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Hammer"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Leverage Machine"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Roller"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Rope"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Skierg Machine"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Sled Machine"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Smith Machine"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Stationary Bike"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Stepmill Machine"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Tire"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Upper Body Ergometer"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Weighted"));
        filteredEquipment.removeIf(e -> e.getName().equalsIgnoreCase("Wheel Roller"));

        return filteredEquipment;
    }
}