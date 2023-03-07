package persistence;

import model.Pokemon;
import model.Trainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static model.Species.*;
import static model.Trainer.Gender.FEMALE;
import static model.Trainer.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    Trainer advancedTrainer; //3 Pokemon, 1 team with 1 Pokemon, 1 team with 2 Pokemon
    Pokemon beedrill, raticate, spearow;

    Trainer masterTrainer; //9 Pokemon, 3 teams with 3 Pokemon, 1 team with 6 Pokemon
    Pokemon bulbasaur, ivysaur, venusaur, charmander, charmeleon, charizard, squirtle, wartortle, blastoise;

    @BeforeEach
    void setup() {
        advancedTrainer = new Trainer("Advanced Girl", FEMALE);
        beedrill = new Pokemon(BEEDRILL);
        raticate = new Pokemon(RATICATE);
        spearow = new Pokemon(SPEAROW);
        advancedTrainer.addPokemonToRanch(beedrill);
        advancedTrainer.addPokemonToRanch(raticate);
        advancedTrainer.addPokemonToRanch(spearow);
        advancedTrainer.makeTeam("Advanced Team A");
        advancedTrainer.makeTeam("Advanced Team B");
        advancedTrainer.addPokemonToTeam(beedrill, "Advanced Team A");
        advancedTrainer.addPokemonToTeam(raticate, "Advanced Team B");
        advancedTrainer.addPokemonToTeam(spearow, "Advanced Team B");

        masterTrainer = new Trainer("Master Boy", MALE);
        bulbasaur = new Pokemon(BULBASAUR);
        ivysaur = new Pokemon(IVYSAUR);
        venusaur = new Pokemon(VENUSAUR);
        charmander = new Pokemon(CHARMANDER);
        charmeleon = new Pokemon(CHARMELEON);
        charizard = new Pokemon(CHARIZARD);
        squirtle = new Pokemon(SQUIRTLE);
        wartortle = new Pokemon(WARTORTLE);
        blastoise = new Pokemon(BLASTOISE);
        masterTrainer.addPokemonToRanch(bulbasaur);
        masterTrainer.addPokemonToRanch(ivysaur);
        masterTrainer.addPokemonToRanch(venusaur);
        masterTrainer.addPokemonToRanch(charmander);
        masterTrainer.addPokemonToRanch(charmeleon);
        masterTrainer.addPokemonToRanch(charizard);
        masterTrainer.addPokemonToRanch(squirtle);
        masterTrainer.addPokemonToRanch(wartortle);
        masterTrainer.addPokemonToRanch(blastoise);
        masterTrainer.makeTeam("Master Grass Team");
        masterTrainer.makeTeam("Master Fire Team");
        masterTrainer.makeTeam("Master Water Team");
        masterTrainer.makeTeam("Grandmaster Team");
        masterTrainer.addPokemonToTeam(bulbasaur, "Master Grass Team");
        masterTrainer.addPokemonToTeam(ivysaur, "Master Grass Team");
        masterTrainer.addPokemonToTeam(venusaur, "Master Grass Team");
        masterTrainer.addPokemonToTeam(charmander, "Master Fire Team");
        masterTrainer.addPokemonToTeam(charmeleon, "Master Fire Team");
        masterTrainer.addPokemonToTeam(charizard, "Master Fire Team");
        masterTrainer.addPokemonToTeam(squirtle, "Master Water Team");
        masterTrainer.addPokemonToTeam(wartortle, "Master Water Team");
        masterTrainer.addPokemonToTeam(blastoise, "Master Water Team");
        masterTrainer.addPokemonToTeam(ivysaur, "Grandmaster Team");
        masterTrainer.addPokemonToTeam(venusaur, "Grandmaster Team");
        masterTrainer.addPokemonToTeam(charmeleon, "Grandmaster Team");
        masterTrainer.addPokemonToTeam(charizard, "Grandmaster Team");
        masterTrainer.addPokemonToTeam(wartortle, "Grandmaster Team");
        masterTrainer.addPokemonToTeam(blastoise, "Grandmaster Team");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            Trainer tr = new Trainer("Invalid");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterBeginningTrainer() {
        try {
            Trainer tr = new Trainer("Beginner Boy");
            JsonWriter writer = new JsonWriter("./data/testWriterBeginningTrainer.json");
            writer.open();
            writer.write(tr);
            writer.close();

            assertEquals("Beginner Boy", tr.getName());
            assertEquals(0, tr.numberPokemon());
            assertEquals(0, tr.numberTeams());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterAdvancedTrainer() {
        try {
            Trainer tr = advancedTrainer;
            JsonWriter writer = new JsonWriter("./data/testWriterAdvancedTrainer.json");
            writer.open();
            writer.write(tr);
            writer.close();

            assertEquals("Advanced Team A:  (1) Beedrill the Beedrill\n"
                            + "Advanced Team B:  (1) Raticate the Raticate (2) Spearow the Spearow\n",
                    tr.detailedListOfTeamsSummary());
            assertEquals("Nickname: Beedrill Species: Beedrill Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Raticate Species: Raticate Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Spearow  Species: Spearow  Gender: ♂ Shiny Status: NOT SHINY\n",
                    tr.detailedRanchSummary());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterMasterTrainer() {
        try {
            Trainer tr = masterTrainer;
            JsonWriter writer = new JsonWriter("./data/testWriterMasterTrainer.json");
            writer.open();
            writer.write(tr);
            writer.close();

            assertEquals("Master Grass Team:  (1) Bulbasaur the Bulbasaur (2) Ivysaur the Ivysaur (3) " +
                            "Venusaur the Venusaur\n" + "Master Fire Team:  (1) Charmander the Charmander (2) " +
                            "Charmeleon the Charmeleon (3) Charizard the Charizard\n" + "Master Water Team:  (1) " +
                            "Squirtle the Squirtle (2) Wartortle the Wartortle (3) Blastoise the Blastoise\n" +
                            "Grandmaster Team:  (1) Ivysaur the Ivysaur (2) Venusaur the Venusaur (3) Charmeleon " +
                            "the Charmeleon (4) Charizard the Charizard (5) Wartortle the Wartortle (6) Blastoise " +
                            "the Blastoise\n",
                    tr.detailedListOfTeamsSummary());
            assertEquals("Nickname: Bulbasaur  Species: Bulbasaur  Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Ivysaur    Species: Ivysaur    Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Venusaur   Species: Venusaur   Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Charmander Species: Charmander Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Charmeleon Species: Charmeleon Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Charizard  Species: Charizard  Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Squirtle   Species: Squirtle   Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Wartortle  Species: Wartortle  Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Blastoise  Species: Blastoise  Gender: ♂ Shiny Status: NOT SHINY\n",
                    tr.detailedRanchSummary());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
