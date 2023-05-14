package com.example.fitnessapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BodyPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private List<BodyGroup> groups;

    public BodyPart(String name) {
        this.name = name;
        groups = new ArrayList<>();
        BodyGroup upperBody = new BodyGroup("Upper Body");
        BodyGroup lowerBody = new BodyGroup("Lower Body");
        BodyGroup fullBody = new BodyGroup("Full Body");

        //bodyparts to bodygroups
        if (name.equals("chest") || name.equals("back") || name.equals("shoulders") ||
            name.equals("neck") || name.equals("upper arms") || name.equals("lower arms")) {
            groups.add(upperBody);
            groups.add(fullBody);
        }
        if (name.equals("lower legs") || name.equals("upper legs")) {
            groups.add(lowerBody);
            groups.add(fullBody);
        }
        if (name.equals("cardio")) {
            groups.add(new BodyGroup("Cardio"));
        }
        if (name.equals("waist")) {
            groups.add(new BodyGroup("Abs"));
        }
    }
}