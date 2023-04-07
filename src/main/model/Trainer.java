package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.sql.Wrapper;
import java.util.*;

// Represents a trainer with a name, gender, list of caught Pokemon, and list of Teams full of Pokemon
public class Trainer implements Writable {
    // Fields ----------------------------------------------------------------------------------------------------------
    private String name;
    private Gender gender;
    private List<Pokemon> ranch;
    private List<Team> teams;
    private EventLog logger;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    // Constructors ----------------------------------------------------------------------------------------------------
    // EFFECTS: constructs a trainer with given name and default gender
    public Trainer(String name) {
        this.name = name;
        gender = Gender.OTHER;
        ranch = new ArrayList<>();
        teams = new ArrayList<>();
        logger = EventLog.getInstance();
    }

    // constructs a trainer with given name and given gender
    public Trainer(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        ranch = new ArrayList<>();
        teams = new ArrayList<>();
        logger = EventLog.getInstance();
    }
    // Constructors ----------------------------------------------------------------------------------------------------

    // Getters and Setters ---------------------------------------------------------------------------------------------
    // EFFECTS: produces the name of the trainer
    public String getName() {
        return name;
    }

    // EFFECTS: produces the gender of the trainer
    public Gender getGender() {
        return gender;
    }

    // EFFECTS: produces all the Pokemon in trainer's ranch
    public List<Pokemon> getRanch() {
        return Collections.unmodifiableList(ranch);
    }

    // EFFECTS: produces all the Trainer's teams
    public List<Team> getTeams() {
        return Collections.unmodifiableList(teams);
    }

    // MODIFIES: this
    // EFFECTS: changes the name of the trainer to given name
    public void setName(String name) {
        logger.logEvent(new Event("Changed Trainer name from " + this.name + " to " + name));
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: changes the gender of the trainer to given gender
    public void setGender(Gender gender) {
        logger.logEvent(new Event("Changed Trainer gender from " + this.gender + " to " + gender));
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
        logger.logEvent(new Event("Added " + p + " to " + this.name + " Ranch"));
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
                logger.logEvent(new Event("Removed " + p + " from " + this.name + " Ranch"));
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
            detailedSummary.append(p.oneLineSummary(longestSpec, longestNick)).append("\n");
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
        logger.logEvent(new Event("Made new team called " + teamName));
        teams.add(new Team(teamName));
    }

    // REQUIRES: team name of te is not same name as any other Team in teams
    // MODIFIES: this
    // EFFECTS: adds te to trainers list of teams
    public void addTeam(Team te) {
        logger.logEvent(new Event("Added " + te + " to Trainer's list of teams"));
        teams.add(te);
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
            logger.logEvent(new Event("Added " + addingPokemon + " to " + addingTeam));
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
            logger.logEvent(new Event("Deleted " + deletingTeam));
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
            detailedSummary.append(t.oneLineSummary()).append("\n");
        }
        return detailedSummary.toString();
    }
    // Team Methods ----------------------------------------------------------------------------------------------------

    // JSON Methods ----------------------------------------------------------------------------------------------------
    // EFFECTS: produces the json representation of this trainer
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("gender", gender);
        json.put("allRanchPokemon", pokemonToJson());
        json.put("allTeams", teamsToJson());
        return json;
    }

    // EFFECTS: returns Pokemon in trainer's ranch as a JSON array
    private JSONArray pokemonToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pokemon p : ranch) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns the trainer's teams as a JSON array
    private JSONArray teamsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : teams) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }
    // JSON Methods ----------------------------------------------------------------------------------------------------

    // Other Methods ---------------------------------------------------------------------------------------------------
    // EFFECTS: produces string representation of the trainer with name, gender, number of Pokemon, and number of Teams
    @Override
    public String toString() {
        return name + " the Trainer!"
                + "\n- Gender: " + gender.name()
                + "\n- Pokemon caught: " + ranch.size()
                + "\n- Teams created: " + teams.size();
    }
    // Other Methods ---------------------------------------------------------------------------------------------------
}
