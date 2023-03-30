package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Bodypart;
import com.example.fitnessapplication.models.Target;
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
public class TargetService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api_key}")
    private String apiKey;

    @Value("${api_host}")
    private String apiHost;

    //List All Target Muscles
//    public List<Target> getAllTargetMuscles() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("X-RapidAPI-Key", apiKey);
//        headers.set("X-RapidAPI-Host", apiHost);
//
//        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
//        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/targetList")
//                .build()
//                .toUri();
//
//        ResponseEntity<List<Target>> response =
//                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Target>>() {
//                });
//
//        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
//            return response.getBody();
//        } else {
//            return null;
//        }
//    }

    public Target[] getAllTargetMuscles() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/targetList")
                .build()
                .toUri();

        ResponseEntity<Target[]> response =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Target[].class);

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }
}