package com.example.fitnessapplication.dto;

import com.example.fitnessapplication.models.BodyGroup;
import com.example.fitnessapplication.models.Equipment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WorkoutRequest {

    private List<Equipment> equipment;
    private BodyGroup bodyGroup;
    private Integer minutes;
}