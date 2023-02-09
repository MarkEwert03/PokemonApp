package model;

import java.util.*;

// Represents a team with name, and up to TEAM_SIZE number of Pokemon
public class Team {
    // Fields ----------------------------------------------------------------------------------------------------------
    private static final int TEAM_SIZE = 6;
    private String name;
    private List<Pokemon> roster;

    // Constructors ----------------------------------------------------------------------------------------------------
    public Team(String name) {
        this.name = name;
        roster = new ArrayList<>();
    }

    // Getters and Setters ---------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public List<Pokemon> getRoster() {
        return roster;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Methods ---------------------------------------------------------------------------------------------------------

    // EFFECTS: returns the number of Pokemon in the team
    public int length() {
        return 0; //stub
    }

    // EFFECTS: returns true if the team is empty, false otherwise
    public boolean isEmpty() {
        return false; //stub
    }

    // EFFECTS: returns true if the team has reached the maximum team size
    public boolean isMaxSize() {
        return false; //stub
    }

    // REQUIRES: p does not have the same species and nickname as another Pokemon in the team
    // MODIFIES: this
    // EFFECTS: length of team < TEAM_SIZE, adds p to the team and returns true, otherwise returns false
    public boolean addPokemon(Pokemon p) {
        return false; //stub
    }

    // REQUIRES: length of team is > 0
    // MODIFIES: this
    // EFFECTS: if p is in the team, removes p and returns true, otherwise returns false
    public boolean removePokemon(Pokemon p) {
        return false; //stub
    }

    // MODIFIES: this
    // EFFECTS: if p is in the team, moves p to the front of the team and shifts the remaining Pokemon down by 1,
    //          otherwise return false
    public boolean moveFront(Pokemon p) {
        return false; //stub
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Team " + name + " consists of:\n");
        for (Pokemon p : roster) {
            output.append(" - ").append(p.toString()).append("\n");
        }
        return output.toString();
    }
}
