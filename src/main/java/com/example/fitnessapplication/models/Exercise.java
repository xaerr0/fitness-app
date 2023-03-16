package com.example.fitnessapplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Exercise {

    private long id;

    private List<Bodypart> bodyPart;
    private List<Equipment> equipment;

    @JsonProperty("gif_url")
    private String exerciseGif;

    private String name;
    private List<Target> target;

}