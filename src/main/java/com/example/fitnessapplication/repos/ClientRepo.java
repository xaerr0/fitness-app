package com.example.fitnessapplication.repos;

import com.example.fitnessapplication.models.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public interface ClientRepo extends JpaRepository<Client, Long> {



}