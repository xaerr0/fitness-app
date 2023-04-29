package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.BodyPart;
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
import java.util.List;

@Service
public class BodyPartService {

    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    BodyPartRepo bodypartRepo;

//    public void pullAndPersistBodyParts() {
//        if (bodypartRepo.findAll().size() == 0) {
//            List<BodyPart> bodyPartList = getAllBodyParts();
//            saveAllBodyParts(bodyPartList);
//        }
//    }
//
//    private void saveAllBodyParts(List<BodyPart> bodyPartList) {
//        for (BodyPart bodypart : bodyPartList) {
//            bodypartRepo.save(bodypart);
//        }
//    }


    public List<BodyPart> getAllBodyParts() {
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/bodyPartList")
                .build()
                .toUri();

        ResponseEntity<List<BodyPart>> response =
                restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<BodyPart>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }
}