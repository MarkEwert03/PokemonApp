package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Pokemon.Species.*;
import static model.Pokemon.Gender.*;
import static org.junit.jupiter.api.Assertions.*;


public class PokemonTest {
    Pokemon weedle;
    Pokemon flower;
    Pokemon zard;
    Pokemon cannon;
    Pokemon angel;

    @BeforeEach
    void setup() {
        weedle = new Pokemon(WEEDLE);

        flower = new Pokemon(RAICHU, "Flower", false, FEMALE);
        zard = new Pokemon(CHARIZARD, "Zard", false, MALE);
        cannon = new Pokemon(BLASTOISE, "Cannon", true, MALE);
        angel = new Pokemon(PIDGEOTTO, "Angel", true, FEMALE);
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
    }

    @Test
    void testSetShiny() {
        assertFalse(zard.getShiny());
        zard.setShiny(true);
        assertTrue(zard.getShiny());
    }

    @Test
    void testSetGender() {
        assertEquals(MALE, cannon.getGender());
        cannon.setGender(FEMALE);
        assertEquals(FEMALE, cannon.getGender());
    }

    @Test
    void testToString() {
        assertEquals("Weedle the Weedle", weedle.toString());
        assertEquals("Angel the Pidgeotto", angel.toString());
    }
}
