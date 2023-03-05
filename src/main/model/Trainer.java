package model;

import org.json.JSONObject;
import persistence.Writable;

import java.sql.Wrapper;
import java.util.*;

// Represents a trainer with a name, gender, list of caught Pokemon, and list of Teams
public class Trainer implements Writable {
    // Fields ----------------------------------------------------------------------------------------------------------
    private String name;
    private Gender gender;
    private List<Pokemon> ranch;
    private List<Team> teams;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    // Constructors ----------------------------------------------------------------------------------------------------
    public Trainer(String name) {
        this.name = name;
        gender = Gender.OTHER;
        ranch = new ArrayList<>();
        teams = new ArrayList<>();
    }

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
    // Getters and Setters ---------------------------------------------------------------------------------------------

    // Pokemon Methods -------------------------------------------------------------------------------------------------

    // EFFECTS: returns the number of Pokemon in the ranch
    public int numberPokemon() {
        return ranch.size();
    }

    // EFFECTS: returns true if there are no Pokemon in the ranch, false otherwise
    public boolean hasZeroPokemon() {
        return numberPokemon() == 0;
    }

    // EFFECTS: returns an array list of all the Pokemon in the Trainer's ranch
    public ArrayList<String> getAllPokemonNicknames() {
        ArrayList<String> nicknames = new ArrayList<>();
        for (Pokemon p : ranch) {
            nicknames.add(p.getNickname());
        }
        return nicknames;
    }

    // REQUIRES: given nickname is a nickname of exactly one Pokemon in the ranch
    // EFFECTS: returns the Pokemon corresponding to the given nickname
    public Pokemon getPokemonFromNickname(String nickname) {
        for (Pokemon p : ranch) {
            if (p.getNickname().equals(nickname)) {
                return p;
            }
        }

        return null;
    }

    // REQUIRES: p cannot have the same nickname as any other Pokemon in the ranch
    // MODIFIES: this
    // EFFECTS: adds p to the ranch
    public void addPokemonToRanch(Pokemon p) {
        ranch.add(p);
    }

    // MODIFIES: this
    // EFFECTS: if Pokemon with nickname nn is in ranch, deletes that Pokemon from the ranch,
    //            deletes that Pokemon from any team that it is in, and returns true,
    //          otherwise returns false
    public boolean deletePokemon(String nn) {
        for (Pokemon p : ranch) {
            if (p.getNickname().equals(nn)) {
                for (Team t : teams) {
                    t.removePokemon(p);
                }
                ranch.remove(p);
                return true;
            }
        }
        return false;
    }

    // EFFECTS: produces all the Pokemon in the trainers ranch
    public String displayRanch() {
        StringBuilder ranchString = new StringBuilder("You have " + numberPokemon() + " Pokemon:");
        for (Pokemon p : ranch) {
            ranchString.append("\n- ").append(p.toString());
        }
        return ranchString.toString();
    }

    // EFFECTS: produces a detailed summary of all the Pokemon in the ranch
    public String detailedRanchSummary() {
        int longestSpec = getLongestSpeciesName();
        int longestNick = getLongestNickname();
        StringBuilder detailedSummary = new StringBuilder();
        for (Pokemon p : ranch) {
            detailedSummary.append("\n").append(p.oneLineSummary(longestSpec, longestNick));
        }
        return detailedSummary.toString();
    }

    // EFFECTS: returns the longest species name of Pokemon in trainer's ranch, 0 if ranch is empty
    private int getLongestSpeciesName() {
        int longestSpecies = 0;
        for (Pokemon p : ranch) {
            longestSpecies = Math.max(longestSpecies, p.getSpecies().name().length());
        }
        return longestSpecies;
    }

    // EFFECTS: returns the longest nickname of Pokemon in trainer's ranch, 0 if ranch is empty
    private int getLongestNickname() {
        int longestNickname = 0;
        for (Pokemon p : ranch) {
            longestNickname = Math.max(longestNickname, p.getNickname().length());
        }
        return longestNickname;
    }
    // Pokemon Methods -------------------------------------------------------------------------------------------------

    // Team Methods ----------------------------------------------------------------------------------------------------
    // EFFECTS: returns the number of teams the trainer has
    public int numberTeams() {
        return teams.size();
    }

    // EFFECTS: returns true if the trainer has no teams, false otherwise
    public boolean hasZeroTeams() {
        return numberTeams() == 0;
    }

    // REQUIRES: given team name is name of exactly one team in list of teams
    // EFFECTS: returns the Team corresponding to the given name
    public Team getTeamFromName(String name) {
        for (Team t : teams) {
            if (t.getName().equals(name)) {
                return t;
            }
        }

        return null;
    }

    // EFFECTS: returns an array list of all the team names in the Trainers list of teams
    public ArrayList<String> getAllTeamNames() {
        ArrayList<String> teamNames = new ArrayList<>();
        for (Team t : teams) {
            teamNames.add(t.getName());
        }
        return teamNames;
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
    public boolean addPokemonToTeam(Pokemon addingPokemon, String addingTeamName) {
        Team addingTeam;
        if (getAllTeamNames().contains(addingTeamName)) {
            addingTeam = getTeamFromName(addingTeamName);
        } else {
            return false;
        }

        if (!addingTeam.containsPokemon(addingPokemon)) {
            addingTeam.addPokemon(addingPokemon);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: if teamName is name of one of teams, removes that team from teams and returns true,
    //          otherwise returns false
    public boolean deleteTeam(String teamName) {
        if (getAllTeamNames().contains(teamName)) {
            Team deletingTeam = getTeamFromName(teamName);
            teams.remove(deletingTeam);
            return true;
        }
        return false;
    }

    // EFFECTS: produces the information about the Pokemon on all the trainers team
    public String displayTeams() {
        String startingString = "You have " + numberTeams() + " team" + ((numberTeams() != 1) ? "s:" : ":");
        StringBuilder teamsString = new StringBuilder(startingString);
        for (Team t : teams) {
            teamsString.append("\n- ").append(t.getName()).append(" (").append(t.length()).append(" Pokemon)");
        }
        return teamsString.toString();
    }

    // EFFECTS: produces a detailed summary of all the Pokemon in the list of teams
    public String detailedListOfTeamsSummary() {
        StringBuilder detailedSummary = new StringBuilder();
        for (Team t : teams) {
            detailedSummary.append("\n").append(t.oneLineSummary());
        }
        return detailedSummary.toString();
    }
    // Team Methods ----------------------------------------------------------------------------------------------------

    // JSON Methods ----------------------------------------------------------------------------------------------------
    @Override
    public JSONObject toJson() {
        return new JSONObject(); //stub
    }
    // JSON Methods ----------------------------------------------------------------------------------------------------

    // Other Methods ---------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return name + " the Trainer!"
                + "\n- Gender: " + gender.name()
                + "\n- Pokemon caught: " + ranch.size()
                + "\n- Teams created: " + teams.size();
    }
    // Other Methods ---------------------------------------------------------------------------------------------------
}
