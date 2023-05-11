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
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

//    private List<FilteredEquipment> groups;


    public Equipment(String name) {
        this.name = name;
        //TODO Later add possibility of equipment groups? Cardio equipment, Gym equipment, Free Weights, etc..
//        groups = new ArrayList<>();
//        FilteredEquipment filteredEquipment = new FilteredEquipment("Filtered Equipment");
//
//        if (name.equals("band") || name.equals("barbell") || name.equals("body weight") || name.equals("bosu ball") ||
//            name.equals("cable") || name.equals("dumbbell") || name.equals("ez barbell") || name.equals("kettlebell") ||
//            name.equals("medicine ball") || name.equals("olympic barbell") || name.equals("resistance band") ||
//            name.equals("stability ball") || name.equals("trap bar")) {
//            groups.add(filteredEquipment);
//        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        // API is lowercase - this capitalizes the first letter of every word
        String[] words = name.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0,1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }
}