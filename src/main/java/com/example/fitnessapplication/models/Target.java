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
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private List<TargetGroup> groups;

    public Target(String target) {
        this.name = target;
        groups = new ArrayList<>();
        TargetGroup upperBody = new TargetGroup("Upper Body Muscles");
        TargetGroup lowerBody = new TargetGroup("Lower Body Muscles");
        TargetGroup fullBody = new TargetGroup("Full Body Muscles");

        if (name.equals("biceps") || name.equals("delts") || name.equals("forearms") || name.equals("lats") ||
            name.equals("levator scapulae") || name.equals("pectorals") || name.equals("serratus anterior") ||
            name.equals("spine") || name.equals("traps") || name.equals("triceps") || name.equals("upper back")) {
            groups.add(upperBody);
            groups.add(fullBody);
        }
        if (name.equals("abductors") || name.equals("adductors") || name.equals("calves") || name.equals("glutes") ||
            name.equals("hamstrings") || name.equals("quads")) {
            groups.add(lowerBody);
            groups.add(fullBody);
        }
        if (name.equals("cardiovascular system")) {
            groups.add(new TargetGroup("Cardio"));
        }
        if (name.equals("abs")) {
            groups.add(new TargetGroup("Abs"));
        }
    }

    @Override
    public String toString() {
        // API is lowercase - this capitalizes the first letter of every word
        String[] words = name.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }
}