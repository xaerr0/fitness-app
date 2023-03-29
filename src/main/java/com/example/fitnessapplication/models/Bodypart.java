package com.example.fitnessapplication.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bodypart {

    private Long id;
    private String bodyPart;

//    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Bodypart(String bodyPart) {
        this.bodyPart = bodyPart;
    }
}