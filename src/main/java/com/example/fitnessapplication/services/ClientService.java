package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Client;
import com.example.fitnessapplication.repos.ClientRepo;
import com.example.fitnessapplication.repos.UserPrincipalRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    @Autowired
    UserPrincipalRepo userRepo;

    @Autowired
    ClientRepo clientRepo;


    public Client saveUser(Client client) {
        return clientRepo.save(client);
    }
}