package com.example.fitnessapplication.services;

import com.example.fitnessapplication.models.Client;
import com.example.fitnessapplication.models.securitymodels.UserPrincipal;
import com.example.fitnessapplication.models.securitymodels.Role;
import com.example.fitnessapplication.repos.UserPrincipalRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Optional;

@Component
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    UserPrincipalRepo userRepo;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPrincipal optionalUser = userRepo.findByUsername(username);

        if (optionalUser == null) {
            throw new UsernameNotFoundException(username + " is not a valid username! Check for typos and try again.");
        }

        return optionalUser;
    }

    @Transactional(readOnly = true)
    public UserPrincipal getUserByUserId(Long userId) throws EntityNotFoundException {
        Optional<UserPrincipal> optional = userRepo.findById(userId);
        UserPrincipal user;
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("User not found.");
        } else {
            user = optional.get();
        }
        //call unproxy() to ensure all related entities are loaded—no lazy load exceptions.
        return (UserPrincipal) Hibernate.unproxy(user);
    }

    @Transactional(readOnly = true)
    public UserPrincipal getUser(String username) throws EntityNotFoundException {
        return userRepo.findByUsername(username);
    }

    public UserPrincipal createNewUser(UserPrincipal userPrincipal) {
//        userDetails.setId(null);
        userPrincipal.getAuthorities().forEach(a -> a.setId(null));

        //override or set user settings to correct values
        userPrincipal.setAccountNonExpired(true);
        userPrincipal.setAccountNonLocked(true);
        userPrincipal.setCredentialsNonExpired(true);
        userPrincipal.setEnabled(true);
        userPrincipal.setAuthorities(Collections.singletonList(new Role(Role.Roles.ROLE_USER)));


        userPrincipal.setUserMeta(new Client(
                userPrincipal.getId(),
                userPrincipal.getEmail()));




        checkPassword(userPrincipal.getPassword());
        userPrincipal.setPassword(encoder.encode(userPrincipal.getPassword()));
        try {
            return userRepo.save(userPrincipal);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e.getCause());
        }
    }

    private void checkPassword(String password) {
        if (password == null) {
            throw new IllegalStateException("You must set a password");
        }
        if (password.length() < 6) {
            throw new IllegalStateException("Password is too short. Must be longer than 6 characters");
        }
    }
}