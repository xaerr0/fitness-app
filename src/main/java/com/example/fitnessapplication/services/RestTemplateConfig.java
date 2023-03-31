package com.example.fitnessapplication.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${api_key}")
    private String apiKey;

    @Value("${api_host}")
    private String apiHost;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {


        return builder
                .defaultHeader("X-RapidAPI-Key", apiKey)
                .defaultHeader("X-RapidAPI-Host", apiHost)
                .build();
    }
}