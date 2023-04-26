package com.example.fitnessapplication.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class WorkoutRequest {

    List<Equipment> equipment;
    List<BodyPart>  bodyParts;

}