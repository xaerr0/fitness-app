package com.example.fitnessapplication;

import com.example.fitnessapplication.models.Exercise;
import com.example.fitnessapplication.repos.ExerciseRepo;
import com.example.fitnessapplication.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class FitnessApplication {

	@Autowired
	ExerciseRepo exerciseRepo;

	public static void main(String[] args) {
		SpringApplication.run(FitnessApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {

		return builder.build();
	}

	@Bean
	public CommandLineRunner run() throws Exception {
		return args -> {
			List<Exercise> exercises = exerciseRepo.findAll();
			exerciseRepo.saveAll(exercises);
			exercises.forEach(System.out::println);
        };
	}
}