package com.example.fitnessapplication.repos;

import com.example.fitnessapplication.models.BodyPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyPartRepo extends JpaRepository<BodyPart, Long> {

}