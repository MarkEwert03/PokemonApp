package model;

// Represents a specific Pokemon that can be added to teams
public class Pokemon {
    // Fields ----------------------------------------------------------------------------------------------------------
    private final Species species;
    private String nickname;
    private boolean shiny;
    private Gender gender;

    public enum Species {
        BULBASAUR, IVYSAUR, VENUSAUR, CHARMANDER, CHARMELEON, CHARIZARD, SQUIRTLE, WARTORTLE, BLASTOISE, CATERPIE,
        METAPOD, BUTTERFREE, WEEDLE, KAKUNA, BEEDRILL, PIDGEY, PIDGEOTTO, PIDGEOT, RATTATA, RATICATE, SPEAROW, FEAROW,
        EKANS, ARBOK, PIKACHU, RAICHU
    }

    public enum Gender {
        MALE, FEMALE, UNKNOWN
    }

    // Constructors ----------------------------------------------------------------------------------------------------
    public Pokemon(Species species) {
        this.species = species;
        this.nickname = capitalize(species.toString());
        this.shiny = false;
        this.gender = Gender.MALE;
    }

    public Pokemon(Species species, String nickname, boolean shiny, Gender gender) {
        this.species = species;
        this.nickname = nickname;
        this.shiny = shiny;
        this.gender = gender;
    }

    // Getters and Setters ---------------------------------------------------------------------------------------------
    public Species getSpecies() {
        return species;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean getShiny() {
        return shiny;
    }

    public Gender getGender() {
        return gender;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setShiny(boolean shiny) {
        this.shiny = shiny;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

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

    // EFFECTS: helper method that makes the first letter of str upper case, and makes the rest lower
    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return nickname + " the " + capitalize(species.toString());
    }
}
