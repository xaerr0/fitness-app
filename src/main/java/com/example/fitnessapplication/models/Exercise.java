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

    private String bodyPart;
    private String equipment;

    @JsonProperty("gif_url")
    private String exerciseGif;

    private String name;
    private String target;
}