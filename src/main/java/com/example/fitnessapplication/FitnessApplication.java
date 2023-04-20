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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.fitnessapplication"})
@EnableJpaRepositories(basePackages = {"com.example.fitnessapplication.repos"})
public class FitnessApplication {


	public static void main(String[] args) {
		SpringApplication.run(FitnessApplication.class, args);
	}
}