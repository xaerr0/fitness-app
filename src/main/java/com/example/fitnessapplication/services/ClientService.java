package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Client;
import com.example.fitnessapplication.repos.ClientRepo;
import com.example.fitnessapplication.repos.UserPrincipalRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    UserPrincipalRepo userRepo;

    @Autowired
    ClientRepo clientRepo;


    @Transactional
    public Client saveUser(Client client) {
        return clientRepo.save(client);
    }

    }