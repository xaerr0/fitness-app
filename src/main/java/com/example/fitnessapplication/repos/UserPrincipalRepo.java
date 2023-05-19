package com.example.fitnessapplication.repos;

import com.example.fitnessapplication.models.securitymodels.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrincipalRepo extends JpaRepository<UserPrincipal, Long> {

    UserPrincipal findByUsername(String username);


}