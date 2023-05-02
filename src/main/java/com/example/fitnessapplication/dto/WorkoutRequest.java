package com.example.fitnessapplication.dto;


import com.example.fitnessapplication.models.BodyGroup;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class WorkoutRequest {

    List<String> equipment;
    BodyGroup bodyGroup;

}