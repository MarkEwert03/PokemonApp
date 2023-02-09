package model;

import java.util.*;

// Represents a trainer with a name, gender, list of caught Pokemon, and list of Teams
public class Trainer {
    // Fields ----------------------------------------------------------------------------------------------------------
    private String name;
    private Gender gender;
    private List<Pokemon> ranch;
    private List<Team> teams;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    // Constructors ----------------------------------------------------------------------------------------------------
    public Trainer(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        ranch = new ArrayList<>();
        teams = new ArrayList<>();
    }

    // Getters and Setters ---------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // Methods ---------------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return name + " the Trainer!\n"
                + "Pokemon caught: " + ranch.size() + "\n"
                + "Teams created: " + teams.size();
    }
}
