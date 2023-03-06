package persistence;

import model.Pokemon;
import model.Trainer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static model.Species.*;
import static model.Trainer.Gender.FEMALE;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    Trainer advancedTrainer; //3 Pokemon, 1 team with 1 Pokemon, 1 team with 2 Pokemon
    Pokemon beedrill, raticate, spearow;

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

            File f = new File("./data/testWriterBeginningTrainer.json");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (NullPointerException e) {
            fail("Beginning Trainer file does not exist");
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

            File f = new File("./data/testWriterAdvancedTrainer.json");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (NullPointerException e) {
            fail("Advanced Trainer file does not exist");
        }
    }
}
