package com.example.fitnessapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @OneToOne
    private String target;

    public Target(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return target;
    }
}