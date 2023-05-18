package com.example.fitnessapplication.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BodyGroup {
    private String name;

    public String getName() {
        return name;
    }
}