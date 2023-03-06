package persistence;

import model.Pokemon;
import model.Trainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static model.Species.*;
import static model.Trainer.Gender.FEMALE;
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
        JsonReader reader = new JsonReader("./data/testWriterBeginningTrainer.json");
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
        JsonReader reader = new JsonReader("./data/testWriterAdvancedTrainer.json");
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
}
