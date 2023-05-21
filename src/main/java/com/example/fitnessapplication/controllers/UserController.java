package com.example.fitnessapplication.controllers;

import com.example.fitnessapplication.dto.WorkoutRequest;
import com.example.fitnessapplication.models.*;
import com.example.fitnessapplication.models.securitymodels.UserPrincipal;
import com.example.fitnessapplication.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    BodyPartService bodypartService;

    @Autowired
    TargetService targetService;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    BodyGroupService bodyGroupService;

    @Autowired
    UserPrincipalService userPrincipalService;

    @Autowired
    ClientService clientService;



    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserPrincipal user = new UserPrincipal();
        user.setClient(new Client());
        model.addAttribute("user", user);
        return "/multistep";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserPrincipal user) {
        userPrincipalService.createNewUser(user);
        clientService.saveUser(user.getClient());
        return "redirect:/generator";
    }

    @GetMapping("/generator")
    public String generatorPage(Model model) {
        List<Equipment> equipmentList = equipmentService.getFilteredEquipment();
        model.addAttribute("equipmentList", equipmentList);
        List<Equipment> equipmentListFirstRow = equipmentList.subList(0, 5);
        List<Equipment> equipmentListSecondRow = equipmentList.subList(5, 9);
        List<Equipment> equipmentListThirdRow = equipmentList.subList(9, 13);

        model.addAttribute("equipmentListFirstRow", equipmentListFirstRow);
        model.addAttribute("equipmentListSecondRow", equipmentListSecondRow);
        model.addAttribute("equipmentListThirdRow", equipmentListThirdRow);

        List<BodyPart> bodyPartList = bodypartService.getAllBodyParts();
        model.addAttribute("bodyPartList", bodyPartList);
        List<Target> targetList = targetService.getAllTargetMuscles();
        model.addAttribute("targetList", targetList);
        List<BodyGroup> bodyGroupList = bodyGroupService.getAllBodyGroups();
        model.addAttribute("bodyGroupList", bodyGroupList);
        WorkoutRequest workoutRequest = new WorkoutRequest();
        model.addAttribute("workoutRequest", workoutRequest);

        return "generator-test";
    }

    @PostMapping("/generated")
    public String submitGenerator(@ModelAttribute("workoutRequest") WorkoutRequest workoutRequest, Model model) {
        List<Exercise> exerciseRequest = exerciseService.getExercises(workoutRequest);
        model.addAttribute("exerciseRequest", exerciseRequest);
        return "workout";
    }

    @GetMapping("/user")
    public UserPrincipal getUser(Authentication authentication) {
        return (UserPrincipal) authentication.getPrincipal();
    }

    @PostMapping("/user")
    public ResponseEntity<?> createNewUser(@RequestBody UserPrincipal userDetails) {
        try {
            return ResponseEntity.ok(userPrincipalService.createNewUser(userDetails));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}