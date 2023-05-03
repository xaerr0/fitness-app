package com.example.fitnessapplication.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


//@Entity
@Getter
@Setter
@NoArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //    @OneToOne
    private String bodyPart;

    //    @OneToOne
    private String equipment;

    //    @OneToOne
    private String name;

    //    @OneToOne
    private String target;

    @JsonProperty("gifUrl")
//    @OneToOne
    private String exerciseGif;

    public String getName() {
        //Capitalize every word
        String[] words = name.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }
}