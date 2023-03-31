package com.example.fitnessapplication.repos;

import com.example.fitnessapplication.models.Bodypart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodypartRepo extends JpaRepository<Bodypart, Long> {

}