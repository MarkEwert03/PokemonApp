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

    @Override
    public String toString() {
        return nickname + " the " + capitalize(species.toString());
    }

    private String capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
