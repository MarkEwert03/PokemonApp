package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    int len0;

    @BeforeEach
    void setup() {
        emptyTeam = new Team("Empty");

        singleTeam = new Team("Single");
        pika = new Pokemon(Species.PIKACHU);
        singleTeam.addPokemon(pika);

        multipleTeam = new Team("Multiple");
        bulba = new Pokemon(Species.BULBASAUR);
        charm = new Pokemon(Species.CHARMANDER);
        squirt = new Pokemon(Species.SQUIRTLE);
        multipleTeam.addPokemon(bulba);
        multipleTeam.addPokemon(charm);
        multipleTeam.addPokemon(squirt);

        fullTeam = new Team("Full");
        cater = new Pokemon(Species.CATERPIE);
        meta = new Pokemon(Species.METAPOD);
        butter = new Pokemon(Species.BUTTERFREE);
        pid = new Pokemon(Species.PIDGEY);
        rat = new Pokemon(Species.RATTATA);
        fear = new Pokemon(Species.FEAROW);
        fullTeam.addPokemon(cater);
        fullTeam.addPokemon(meta);
        fullTeam.addPokemon(butter);
        fullTeam.addPokemon(pid);
        fullTeam.addPokemon(rat);
        fullTeam.addPokemon(fear);

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
    void testSetName() {
        assertEquals("Empty", emptyTeam.getName());
        emptyTeam.setName("New Empty");
        assertEquals("New Empty", emptyTeam.getName());

        int len1 = getEventLogLength();
        emptyTeam.setName("New Empty");
        int len2 = getEventLogLength();
        assertEquals(len0 + 1, len1);
        assertEquals(len1, len2);
    }

    @Test
    void testGetRoster() {
        List<Pokemon> gottenRoster = multipleTeam.getRoster();
        assertEquals(bulba, gottenRoster.get(0));
        assertEquals(charm, gottenRoster.get(1));
        assertEquals(squirt, gottenRoster.get(2));
    }

    @Test
    void testLength() {
        assertEquals(0, emptyTeam.length());
        assertEquals(1, singleTeam.length());
        assertEquals(3, multipleTeam.length());
        assertEquals(6, fullTeam.length());
    }

    @Test
    void testIsEmpty() {
        assertTrue(emptyTeam.isEmpty());
        assertFalse(singleTeam.isEmpty());
        assertFalse(multipleTeam.isEmpty());
        assertFalse(fullTeam.isEmpty());
    }

    @Test
    void testIsMaxSize() {
        assertFalse(emptyTeam.isMaxSize());
        assertFalse(singleTeam.isMaxSize());
        assertFalse(multipleTeam.isMaxSize());
        assertTrue(fullTeam.isMaxSize());
    }

    @Test
    void testSummary() {
        assertEquals("Empty is empty", emptyTeam.summary());
        assertEquals("Team Single consists of:\n - Pikachu the Pikachu", singleTeam.summary());
    }

    @Test
    void testOneLineSummary() {
        assertEquals("Empty is empty", emptyTeam.oneLineSummary());
        String mult = "Multiple:  (1) Bulbasaur the Bulbasaur (2) Charmander the Charmander (3) Squirtle the Squirtle";
        assertEquals(mult, multipleTeam.oneLineSummary());
    }

    @Test
    void testContainsPokemon() {
        assertTrue(multipleTeam.containsPokemon(bulba));
        assertFalse(multipleTeam.containsPokemon(pika));
        assertFalse(emptyTeam.containsPokemon(pika));
    }

    @Test
    void testAddPokemonEmptyTeam() {
        Pokemon testP = new Pokemon(Species.EKANS);
        assertTrue(emptyTeam.addPokemon(testP));
        assertFalse(emptyTeam.isEmpty());
        assertEquals(1, emptyTeam.length());
    }

    @Test
    void testAddPokemonFullTeam() {
        Pokemon testP = new Pokemon(Species.ARBOK);
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
    void testRemovePokemonFullTeam() {
        assertTrue(fullTeam.removePokemon(rat));
        assertFalse(fullTeam.isMaxSize());
        assertEquals(TEAM_SIZE-1, fullTeam.length());
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
    void testToString() {
        assertEquals("Empty (0 Pokemon)", emptyTeam.toString());
        assertEquals("Single (1 Pokemon)", singleTeam.toString());
        assertEquals("Multiple (3 Pokemon)", multipleTeam.toString());
        assertEquals("Full (6 Pokemon)", fullTeam.toString());
    }
}