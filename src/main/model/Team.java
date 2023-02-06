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

    // Methods ---------------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
