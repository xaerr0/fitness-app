package com.example.fitnessapplication.services;

public class CapitalizeEveryWord {

    public String capitalizeEveryWord(String str) {
        String[] words = str.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1);
        }
        return String.join(" ", words);
    }

    public String capitalizeEveryWord2(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}