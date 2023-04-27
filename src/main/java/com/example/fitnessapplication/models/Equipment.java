package com.example.fitnessapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    @OneToOne
    private String equipment;

    public Equipment(String equipment) {
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        // API is lowercase - this capitalizes the first letter of every word
        String[] words = equipment.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }
}