package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a specific Pokemon with species, nickname, gender, and shiny status
public class Pokemon implements Writable {
    // Fields ----------------------------------------------------------------------------------------------------------
    private final Species species;
    private String nickname;
    private boolean shiny;
    private Gender gender;
    private EventLog logger;

    public enum Gender {
        MALE, FEMALE, UNKNOWN
    }

    // Constructors ----------------------------------------------------------------------------------------------------
    // EFFECTS: constructs a Pokemon with given species and default nickname, gender, and shiny status
    public Pokemon(Species species) {
        this.species = species;
        this.nickname = capitalize(species.toString());
        this.shiny = false;
        this.gender = Gender.MALE;
        logger = EventLog.getInstance();
    }

    // EFFECTS: constructs a Pokemon with given species, nickname, gender, and shiny status
    public Pokemon(Species species, String nickname, boolean shiny, Gender gender) {
        this.species = species;
        this.nickname = nickname;
        this.shiny = shiny;
        this.gender = gender;
        logger = EventLog.getInstance();
    }
    // Constructors ----------------------------------------------------------------------------------------------------

    // Getters and Setters ---------------------------------------------------------------------------------------------
    // EFFECTS: produces the species of the Pokemon
    public Species getSpecies() {
        return species;
    }

    // EFFECTS: produces the nickname of the Pokemon
    public String getNickname() {
        return nickname;
    }

    // EFFECTS: produces the shiny status of the Pokemon
    public boolean getShiny() {
        return shiny;
    }

    // EFFECTS: produces the gender of the Pokemon
    public Gender getGender() {
        return gender;
    }

    // MODIFIES: this
    // EFFECTS: changes the nickname of the Pokemon to given nickname
    public void setNickname(String nickname) {
        if (!this.nickname.equals(nickname)) {
            logger.logEvent(new Event("Changed " + this.nickname + "'s nickname from "
                    + this.nickname + " to " + nickname));
        }
        this.nickname = nickname;
    }

    // MODIFIES: this
    // EFFECTS: changes the shiny status of the Pokemon to given shiny status
    public void setShiny(boolean shiny) {
        if (this.shiny != shiny) {
            logger.logEvent(new Event("Changed " + nickname + "'s shiny status from "
                    + this.shiny + " to " + shiny));
        }
        this.shiny = shiny;
    }

    // MODIFIES: this
    // EFFECTS: changes the gender of the Pokemon to given gender
    public void setGender(Gender gender) {
        if (this.gender != gender) {
            logger.logEvent(new Event("Changed " + nickname + "'s gender from "
                    + this.gender + " to " + gender));
        }
        this.gender = gender;
    }
    // Getters and Setters ---------------------------------------------------------------------------------------------

    // Methods ---------------------------------------------------------------------------------------------------------
    // EFFECTS: returns true if spec is a valid Pokemon species
    public static boolean isValidSpecies(String spec) {
        for (Species s : Species.values()) {
            if (spec.equalsIgnoreCase(s.name())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: displays the summary of this Pokemon over multiple lines
    public String summary() {
        String summary = "";
        summary += "Nickname: " + nickname;
        summary += "\nSpecies: " + capitalize(species.name());
        summary += "\nGender: " + getGenderSymbol();
        summary += "\nShiny Status: " + getShinySymbol();
        return summary;
    }

    // EFFECTS: displays the summary of this Pokemon in one line with spacing based on longestSpec and longestNick
    public String oneLineSummary(int longestSpec, int longestNick) {
        String summary = "";
        summary += "Nickname: " + nickname + repeatString(longestNick - nickname.length(), " ");
        summary += " Species: " + capitalize(species.name())
                + repeatString(longestSpec - species.name().length(), " ");
        summary += " Gender: " + getGenderSymbol();
        summary += " Shiny Status: " + getShinySymbol();
        return summary;
    }

    // EFFECTS: returns the symbols showing whether Pokemon is shiny or not
    private String getShinySymbol() {
        if (shiny) {
            return "⭐SHINY⭐";
        } else {
            return "NOT SHINY";
        }
    }

    // EFFECTS: returns the respective symbol corresponding to the Pokemon's gender
    private String getGenderSymbol() {
        if (gender.equals(Gender.MALE)) {
            return "♂";
        } else if (gender.equals(Gender.FEMALE)) {
            return "♀";
        } else {
            return "?";
        }
    }

    // EFFECTS returns str n number of times
    public static String repeatString(int n, String str) {
        StringBuilder rep = new StringBuilder();
        for (int i = 0; i < n; i++) {
            rep.append(str);
        }
        return rep.toString();
    }
    // Methods ---------------------------------------------------------------------------------------------------------

    // JSON Methods ----------------------------------------------------------------------------------------------------
    // EFFECTS: produces the json representation of the Pokemon
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("species", species);
        json.put("nickname", nickname);
        json.put("gender", gender);
        json.put("shiny", shiny);
        return json;
    }
    // JSON Methods ----------------------------------------------------------------------------------------------------

    // Other Methods ---------------------------------------------------------------------------------------------------
    // EFFECTS: helper method that makes the first letter of str upper case, and makes the rest lower
    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    // EFFECTS: produces the nickname and species of the Pokemon as a String
    @Override
    public String toString() {
        return nickname + " the " + capitalize(species.toString());
    }
    // Other Methods ---------------------------------------------------------------------------------------------------
}
