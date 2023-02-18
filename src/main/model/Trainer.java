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

    // EFFECTS: returns the number of Pokemon in the ranch
    public int numberPokemon() {
        return 0; //stub
    }

    // EFFECTS: returns true if there are no Pokemon in the ranch, false otherwise
    public boolean hasZeroPokemon() {
        return false; //stub
    }

    // EFFECTS: returns the number of teams the Trainer has
    public int numberTeams() {
        return 0; //stub
    }

    // REQUIRES: p cannot have the same nickname as any other Pokemon in the ranch
    // MODIFIES: this
    // EFFECTS: adds p to the ranch
    public void addPokemonToRanch(Pokemon p) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: if p is in ranch, removes p from the ranch, removes p from any team that p is in, and returns true,
    //          otherwise returns false
    public boolean deletePokemon(Pokemon p) {
        return false; //stub
    }

    // REQUIRES: teamName is not same name as any other Team in teams
    // MODIFIES: this
    // EFFECTS: makes a new empty Team and adds it to trainers list of teams
    public void makeTeam(String teamName) {
        //stub
    }

    // REQUIRES: p is in ranch
    // MODIFIES: this
    // EFFECTS: if teamName is name of one of the Trainer's teams and Pokemon p is not in that team,
    //          adds p to t and returns true, otherwise returns false
    public boolean addPokemonToTeam(Pokemon p, String teamName) {
        return false; //stub
    }

    // MODIFIES: this
    // EFFECTS: if t is in teams, removes t from teams and returns true,
    //          otherwise returns false
    public boolean deleteTeam(Team t) {
        return false; //stub
    }

    @Override
    public String toString() {
        return name + " the Trainer!\n"
                + "Pokemon caught: " + ranch.size() + "\n"
                + "Teams created: " + teams.size();
    }
}
