package com.example.fitnessapplication.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.persistence.*;

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
        return equipment;
    }
}