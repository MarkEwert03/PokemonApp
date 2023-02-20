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
        return ranch.size();
    }

    // EFFECTS: returns true if there are no Pokemon in the ranch, false otherwise
    public boolean hasZeroPokemon() {
        return numberPokemon() == 0;
    }

    // EFFECTS: returns the number of teams the Trainer has
    public int numberTeams() {
        return teams.size();
    }

    // EFFECTS: produces all the Pokemon in the trainers ranch
    public String displayRanch() {
        String ranchString = "You have " + numberPokemon() + " Pokemon:";
        for (Pokemon p : ranch) {
            ranchString += "\n- " + p.toString();
        }
        return ranchString;
    }

    // EFFECTS: produces the information about the Pokemon on all the trainers team
    public String displayTeams() {
        String teamsString = "You have " + numberTeams() + " team" + ((numberTeams() != 1) ? "s:" : ":");
        for (Team t : teams) {
            teamsString += "\n- " + t.getName() + " (" + t.length() + " Pokemon)";
        }
        return teamsString;
    }

    // REQUIRES: p cannot have the same nickname as any other Pokemon in the ranch
    // MODIFIES: this
    // EFFECTS: adds p to the ranch
    public void addPokemonToRanch(Pokemon p) {
        ranch.add(p);
    }

    // REQUIRES: teamName is not same name as any other Team in teams
    // MODIFIES: this
    // EFFECTS: makes a new empty Team and adds it to trainers list of teams
    public void makeTeam(String teamName) {
        teams.add(new Team(teamName));
    }

    // REQUIRES: p is in ranch
    // MODIFIES: this
    // EFFECTS: if teamName is name of one of the Trainer's teams and Pokemon p is not in that team,
    //          adds p to t and returns true, otherwise returns false
    public boolean addPokemonToTeam(Pokemon p, String teamName) {
        for (Team t : teams) {
            if (t.getName().equals(teamName) && !t.containsPokemon(p)) {
                t.addPokemon(p);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if teamName is name of one of teams, removes that team from teams and returns true,
    //          otherwise returns false
    public boolean deleteTeam(String teamName) {
        for (Team t : teams) {
            if (t.getName().equals(teamName)) {
                teams.remove(t);
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if Pokemon with nickname nn is in ranch, deletes that Pokemon from the ranch,
    //            deletes that Pokemon from any team that it is in, and returns true,
    //          otherwise returns false
    public boolean deletePokemon(String nn) {
        for (Pokemon p : ranch) {
            if (p.getNickname().equals(nn)) {
                for (Team t: teams) {
                    t.removePokemon(p);
                }
                ranch.remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name + " the Trainer!"
                + "\n- Pokemon caught: " + ranch.size()
                + "\n- Teams created: " + teams.size();
    }
}
