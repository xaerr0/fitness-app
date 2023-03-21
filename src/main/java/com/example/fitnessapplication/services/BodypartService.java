package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Bodypart;
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
public class BodypartService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api_key}")
    private String apiKey;

    @Value("${api_host}")
    private String apiHost;

    public List<Bodypart> getAllBodyParts() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", apiKey);
        headers.set("X-RapidAPI-Host", apiHost);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        URI uri = UriComponentsBuilder.fromUriString("https://exercisedb.p.rapidapi.com/exercises/bodyPartList")
                .build()
                .toUri();

        ResponseEntity<List<Bodypart>> response =
                restTemplate.exchange(uri, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<Bodypart>>() {
                });

        if (response.getStatusCode().equals(HttpStatus.OK) && response.getBody() != null) {
            return response.getBody();
        } else {
            return null;
        }
    }
}