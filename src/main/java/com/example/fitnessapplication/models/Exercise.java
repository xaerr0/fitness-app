package com.example.fitnessapplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    private String bodyPart;

    @ManyToMany
    private String equipment;

    @OneToOne
    private String name;

    @ManyToMany
    private String target;

    @JsonProperty("gifUrl")
    @OneToOne
    private String exerciseGif;
}