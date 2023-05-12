package com.example.fitnessapplication.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BodyGroup {
    private String name;

    @Override
    public String toString() {
        name = name.replace(" ", "-");
        return name;
    }
}