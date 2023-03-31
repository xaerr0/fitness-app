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
public class BodyPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bodyPart;

    public BodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }
}