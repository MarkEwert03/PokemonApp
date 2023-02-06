package model;

// Represents a specific Pokemon that can be added to teams
public class Pokemon {
    // Fields ----------------------------------------------------------------------------------------------------------
    private String species;
    private String nickname;
    private boolean shiny;
    private Gender gender;

    public enum Gender {
        MALE, FEMALE, UNKNOWN;
    }

    // Constructors ----------------------------------------------------------------------------------------------------
    public Pokemon(String species, String nickname, boolean shiny, Gender gender) {
        this.species = species;
        this.nickname = nickname;
        this.shiny = shiny;
        this.gender = gender;
    }

    // Methods ---------------------------------------------------------------------------------------------------------
    public String getSpecies() {
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

    @Override
    public String toString() {
        return nickname + " the " + species;
    }
}
