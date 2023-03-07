package persistence;

import model.Trainer;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// code adapted from the workroom sample application

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Trainer tr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderBeginningTrainer() {
        JsonReader reader = new JsonReader("./data/testReaderBeginningTrainer.json");
        try {
            Trainer tr = reader.read();
            assertEquals("Beginner Boy", tr.getName());
            assertEquals(0, tr.numberPokemon());
            assertEquals(0, tr.numberTeams());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderAdvancedTrainer() {
        JsonReader reader = new JsonReader("./data/testReaderAdvancedTrainer.json");
        try {
            Trainer tr = reader.read();
            assertEquals("Advanced Team A:  (1) Beedrill the Beedrill\n"
                            + "Advanced Team B:  (1) Raticate the Raticate (2) Spearow the Spearow\n",
                    tr.detailedListOfTeamsSummary());
            assertEquals("Nickname: Beedrill Species: Beedrill Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Raticate Species: Raticate Gender: ♂ Shiny Status: NOT SHINY\n" +
                            "Nickname: Spearow  Species: Spearow  Gender: ♂ Shiny Status: NOT SHINY\n",
                    tr.detailedRanchSummary());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderMasterTrainer() {
        JsonReader reader = new JsonReader("./data/testReaderMasterTrainer.json");
        try {
            Trainer tr = reader.read();
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
            fail("Couldn't read from file");
        }
    }
}
