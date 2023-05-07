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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Equipment> getFilteredEquipment() {

        List<Equipment> filteredEquipment = getAllEquipment();
        filteredEquipment.stream().anyMatch(equipment -> equipment.getGroups().stream()
                .anyMatch(group -> group.getName().equalsIgnoreCase(equipment.getName())));




    return filteredEquipment;

    }
}