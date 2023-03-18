package com.example.fitnessapplication.models;

import com.example.fitnessapplication.services.BodypartService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BodyPartResponse {

    private Bodypart[] bodyParts;
}