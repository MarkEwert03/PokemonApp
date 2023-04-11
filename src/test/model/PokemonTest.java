package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Species.*;
import static model.Pokemon.Gender.*;
import static org.junit.jupiter.api.Assertions.*;


public class PokemonTest {
    Pokemon weedle;
    Pokemon flower;
    Pokemon zard;
    Pokemon cannon;
    Pokemon angel;
    Pokemon unknownGender;

    int len0;

    @BeforeEach
    void setup() {
        weedle = new Pokemon(WEEDLE);

        flower = new Pokemon(RAICHU, "Flower", false, FEMALE);
        zard = new Pokemon(CHARIZARD, "Zard", false, MALE);
        cannon = new Pokemon(BLASTOISE, "Cannon", true, MALE);
        angel = new Pokemon(PIDGEOTTO, "Angel", true, FEMALE);
        unknownGender = new Pokemon(BULBASAUR, "UG", false, UNKNOWN);

        len0 = getEventLogLength();
    }

    // EFFECTS: helper to get number of events in event log
    private int getEventLogLength() {
        int len = 0;
        for (Event event : EventLog.getInstance()) {
            len++;
        }
        return len;
    }

    @Test
    void testGetSpecies() {
        assertEquals(WEEDLE, weedle.getSpecies());
    }

    @Test
    void testSetNickname() {
        assertEquals("Flower", flower.getNickname());
        flower.setNickname("Bloom");
        assertEquals("Bloom", flower.getNickname());

        int len1 = getEventLogLength();
        flower.setNickname("Bloom");
        int len2 = getEventLogLength();
        assertEquals(len0 + 1, len1);
        assertEquals(len1, len2);
    }

    @Test
    void testSetShiny() {
        assertFalse(zard.getShiny());
        zard.setShiny(true);
        assertTrue(zard.getShiny());

        int len1 = getEventLogLength();
        zard.setShiny(true);
        int len2 = getEventLogLength();
        assertEquals(len0 + 1, len1);
        assertEquals(len1, len2);
    }

    @Test
    void testSetGender() {
        assertEquals(MALE, cannon.getGender());
        cannon.setGender(FEMALE);
        assertEquals(FEMALE, cannon.getGender());

        int len1 = getEventLogLength();
        cannon.setGender(FEMALE);
        int len2 = getEventLogLength();
        assertEquals(len0 + 1, len1);
        assertEquals(len1, len2);
    }
    @Test
    void testIsValidSpecies() {
        assertTrue(Pokemon.isValidSpecies("bulbasaur"));
        assertTrue(Pokemon.isValidSpecies("Charmander"));
        assertTrue(Pokemon.isValidSpecies("SqUiRtLe"));
        assertFalse(Pokemon.isValidSpecies("pika chu"));
        assertFalse(Pokemon.isValidSpecies("pokeman"));
    }

    @Test
    void testSummary() {
        String weedleSummary = "Nickname: Weedle\nSpecies: Weedle\nGender: ♂\nShiny Status: NOT SHINY";
        assertEquals(weedleSummary, weedle.summary());

        String angelSummary = "Nickname: Angel\nSpecies: Pidgeotto\nGender: ♀\nShiny Status: ⭐SHINY⭐";
        assertEquals(angelSummary, angel.summary());

        String ugSummary = "Nickname: UG\nSpecies: Bulbasaur\nGender: ?\nShiny Status: NOT SHINY";
        assertEquals(ugSummary, unknownGender.summary());
    }

    @Test
    void testOneLineSummary() {
        String weedleSummary = "Nickname: Weedle Species: Weedle Gender: ♂ Shiny Status: NOT SHINY";
        assertEquals(weedleSummary, weedle.oneLineSummary(6, 6));

        String flowerSummary = "Nickname: Flower   Species: Raichu  Gender: ♀ Shiny Status: NOT SHINY";
        assertEquals(flowerSummary, flower.oneLineSummary(7, 8));

        String ugSummary = "Nickname: UG         Species: Bulbasaur  Gender: ? Shiny Status: NOT SHINY";
        assertEquals(ugSummary, unknownGender.oneLineSummary(10, 10));
    }

    @Test
    void testToJson() {
        String weedleJson = "{\"gender\":\"MALE\",\"species\":\"WEEDLE\",\"nickname\":\"Weedle\",\"shiny\":false}";
        assertEquals(weedleJson, weedle.toJson().toString());

        String pidJson = "{\"gender\":\"FEMALE\",\"species\":\"PIDGEOTTO\",\"nickname\":\"Angel\",\"shiny\":true}";
        assertEquals(pidJson, angel.toJson().toString());
    }

    @Test
    void testToString() {
        assertEquals("Weedle the Weedle", weedle.toString());
        assertEquals("Angel the Pidgeotto", angel.toString());
    }
}
