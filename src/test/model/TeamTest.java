package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Team.TEAM_SIZE;
import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    Team emptyTeam;

    Pokemon pika;
    Team singleTeam;

    Pokemon bulba;
    Pokemon charm;
    Pokemon squirt;
    Team multipleTeam;

    Pokemon cater;
    Pokemon meta;
    Pokemon butter;
    Pokemon pid;
    Pokemon rat;
    Pokemon fear;
    Team fullTeam;

    @BeforeEach
    void setup() {
        emptyTeam = new Team("Empty");

        singleTeam = new Team("Single");
        pika = new Pokemon(Pokemon.Species.PIKACHU);
        singleTeam.addPokemon(pika);

        multipleTeam = new Team("Multiple");
        bulba = new Pokemon(Pokemon.Species.BULBASAUR);
        charm = new Pokemon(Pokemon.Species.CHARMANDER);
        squirt = new Pokemon(Pokemon.Species.SQUIRTLE);
        multipleTeam.addPokemon(bulba);
        multipleTeam.addPokemon(charm);
        multipleTeam.addPokemon(squirt);

        fullTeam = new Team("Full");
        cater = new Pokemon(Pokemon.Species.CATERPIE);
        meta = new Pokemon(Pokemon.Species.METAPOD);
        butter = new Pokemon(Pokemon.Species.BUTTERFREE);
        pid = new Pokemon(Pokemon.Species.PIDGEY);
        rat = new Pokemon(Pokemon.Species.RATTATA);
        fear = new Pokemon(Pokemon.Species.FEAROW);
        fullTeam.addPokemon(cater);
        fullTeam.addPokemon(meta);
        fullTeam.addPokemon(butter);
        fullTeam.addPokemon(pid);
        fullTeam.addPokemon(rat);
        fullTeam.addPokemon(fear);
    }

    @Test
    void testSetName() {
        assertEquals("Empty", emptyTeam.getName());
        emptyTeam.setName("New Empty");
        assertEquals("New Empty", emptyTeam.getName());
    }

    @Test
    void testLengthEmptyTeam() {
        assertEquals(0, emptyTeam.length());
    }

    @Test
    void testIsEmptyEmptyTeam() {
        assertTrue(emptyTeam.isEmpty());
    }

    @Test
    void testIsMaxSizeFullTeam() {
        assertTrue(fullTeam.isMaxSize());
    }

    @Test
    void testAddPokemonEmptyTeam() {
        assertEquals(emptyTeam.getRoster(), new ArrayList<Pokemon>());
        Pokemon testP = new Pokemon(Pokemon.Species.EKANS);
        assertTrue(emptyTeam.addPokemon(testP));
        assertFalse(emptyTeam.isEmpty());
        assertEquals(1, emptyTeam.length());
    }

    @Test
    void testAddPokemonFullTeam() {
        Pokemon testP = new Pokemon(Pokemon.Species.ARBOK);
        assertFalse(fullTeam.addPokemon(testP));
        assertTrue(fullTeam.isMaxSize());
        assertEquals(TEAM_SIZE, fullTeam.length());
    }

    @Test
    void testRemovePokemonSingleTeam() {
        assertTrue(singleTeam.removePokemon(pika));
        assertTrue(singleTeam.isEmpty());
        assertEquals(0, singleTeam.length());
    }

    @Test
    void testRemovePokemonMultipleTeam() {
        assertTrue(multipleTeam.removePokemon(charm));
        assertEquals(2, multipleTeam.length());
    }

    @Test
    void testRemovePokemonNotInTeam() {
        assertFalse(fullTeam.removePokemon(pika));
        assertEquals(TEAM_SIZE,fullTeam.length());
    }

    @Test
    void testGetPokemon() {
        assertEquals(0, multipleTeam.getPokemonIndex(bulba));
        assertEquals(1, multipleTeam.getPokemonIndex(charm));
        assertEquals(2, multipleTeam.getPokemonIndex(squirt));
    }

    @Test
    void testGetPokemonIndexNotInTeam() {
        assertEquals(-1, fullTeam.getPokemonIndex(pika));
    }

    @Test
    void testMoveFrontSingleTeam() {
        assertEquals(0, singleTeam.getPokemonIndex(pika));
        assertTrue(singleTeam.moveFront(pika));
        assertEquals(0, singleTeam.getPokemonIndex(pika));
    }

    @Test
    void testMoveFrontMultipleTeamSecondPokemon() {
        assertTrue(multipleTeam.moveFront(charm));
        assertEquals(0, multipleTeam.getPokemonIndex(charm));
        assertEquals(1, multipleTeam.getPokemonIndex(bulba));
        assertEquals(2, multipleTeam.getPokemonIndex(squirt));
    }

    @Test
    void testMoveFrontMultipleTeamThirdPokemon() {
        assertTrue(multipleTeam.moveFront(squirt));
        assertEquals(0, multipleTeam.getPokemonIndex(squirt));
        assertEquals(1, multipleTeam.getPokemonIndex(bulba));
        assertEquals(2, multipleTeam.getPokemonIndex(charm));
    }

    @Test
    void testMoveFrontNotInTeam() {
        assertFalse(emptyTeam.moveFront(fear));
    }

    @Test
    void testToStringEmptyTeam() {
        String expectedString = "Team " + emptyTeam.getName() + " consists of:\n";
        assertEquals(expectedString, emptyTeam.toString());
    }

    @Test
    void testToStringSingleTeam() {
        String expectedString = "Team " + singleTeam.getName() + " consists of:\n";
        expectedString += " - " + pika.toString() + "\n";
        assertEquals(expectedString, singleTeam.toString());
    }

    @Test
    void testToStringMultipleTeam() {
        String expectedString = "Team " + multipleTeam.getName() + " consists of:\n";
        expectedString += " - " + bulba.toString() + "\n";
        expectedString += " - " + charm.toString() + "\n";
        expectedString += " - " + squirt.toString() + "\n";
        assertEquals(expectedString, multipleTeam.toString());
    }
}