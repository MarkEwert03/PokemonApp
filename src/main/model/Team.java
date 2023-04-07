package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a team with name, and up to TEAM_SIZE number of Pokemon
public class Team implements Writable {
    // Fields ----------------------------------------------------------------------------------------------------------
    public static final int TEAM_SIZE = 6;
    private String name;
    private List<Pokemon> roster;
    private EventLog logger;

    // Constructors ----------------------------------------------------------------------------------------------------
    // EFFECTS: constructs a team with given name
    public Team(String name) {
        this.name = name;
        roster = new ArrayList<>();
        logger = EventLog.getInstance();
    }
    // Constructors ----------------------------------------------------------------------------------------------------

    // Getters and Setters ---------------------------------------------------------------------------------------------
    // EFFECTS: produces the name of the team
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: changes the name of the team to given name
    public void setName(String name) {
        logger.logEvent(new Event("Changed Team name from " + this.name + " to " + name));
        this.name = name;
    }

    public List<Pokemon> getRoster() {
        return Collections.unmodifiableList(roster);
    }
    // Getters and Setters ---------------------------------------------------------------------------------------------

    // Methods ---------------------------------------------------------------------------------------------------------
    // EFFECTS: returns the number of Pokemon in the team
    public int length() {
        return roster.size();
    }

    // EFFECTS: returns true if the team is empty, false otherwise
    public boolean isEmpty() {
        return length() == 0;
    }

    // EFFECTS: returns true if the team has reached the maximum team size
    public boolean isMaxSize() {
        return length() == TEAM_SIZE;
    }

    // EFFECTS: returns true if p is in the team, false otherwise
    public boolean containsPokemon(Pokemon pokemon) {
        String nick = pokemon.getNickname();
        for (Pokemon p : roster) {
            if (p.getNickname().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: displays the summary of this Team over multiple lines
    public String summary() {
        if (isEmpty()) {
            return name + " is empty";
        } else {
            StringBuilder output = new StringBuilder("Team " + name + " consists of:");
            for (Pokemon p : roster) {
                output.append("\n - ").append(p.toString());
            }
            return output.toString();
        }
    }

    // EFFECTS: displays the summary of this Team in one line
    public String oneLineSummary() {
        if (isEmpty()) {
            return name + " is empty";
        } else {
            StringBuilder output = new StringBuilder(name + ": ");
            for (int i = 0; i < length(); i++) {
                Pokemon p = roster.get(i);
                output.append(" (").append(i + 1).append(") ").append(p.toString());
            }
            return output.toString();
        }
    }

    // REQUIRES: p does not have the nickname as another Pokemon in the team
    // MODIFIES: this
    // EFFECTS: length of team < TEAM_SIZE, adds p to the team and returns true, otherwise returns false
    public boolean addPokemon(Pokemon p) {
        if (length() < TEAM_SIZE) {
            logger.logEvent(new Event("Added " + p + " to " + this));
            roster.add(p);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: length of team is > 0
    // MODIFIES: this
    // EFFECTS: if p is in the team, removes p from roster and returns true, otherwise returns false
    public boolean removePokemon(Pokemon p) {
        if (roster.contains(p)) {
            logger.logEvent(new Event("Removed " + p + " from " + this));
            roster.remove(p);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: if p is in the team, returns the 0-based index of p in the team, returns -1 otherwise
    public int getPokemonIndex(Pokemon p) {
        return roster.indexOf(p);
    }

    // MODIFIES: this
    // EFFECTS: if p is in the team, moves p to the front of the team and shifts the remaining Pokemon down by 1,
    //          otherwise return false
    public boolean moveFront(Pokemon p) {
        if (roster.contains(p)) {
            logger.logEvent(new Event("Moved " + p + " to front of " + this));
            int index = getPokemonIndex(p);
            for (int i = length() - 1; i > 0; i--) {
                if (i <= index) {
                    Pokemon shiftP = roster.get(i - 1);
                    roster.set(i, shiftP);
                }
            }
            roster.set(0, p);
            return true;
        } else {
            return false;
        }
    }
    // Methods ---------------------------------------------------------------------------------------------------------

    // JSON Methods ----------------------------------------------------------------------------------------------------
    // EFFECTS: produces the json representation of the team
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("teamName", name);
        json.put("allTeamPokemon", pokemonToJson());
        return json;
    }

    // EFFECTS: returns Pokemon in the team as a JSON array
    private JSONArray pokemonToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pokemon p : roster) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
    // JSON Methods ----------------------------------------------------------------------------------------------------

    // Other Methods ---------------------------------------------------------------------------------------------------
    // EFFECTS: produces text representation of team with name and length
    @Override
    public String toString() {
        return name + " (" + length() + " Pokemon)";
    }
    // Other Methods ---------------------------------------------------------------------------------------------------
}
