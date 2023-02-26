package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Trainer.Gender.*;
import static model.Pokemon.Species.*;
import static org.junit.jupiter.api.Assertions.*;


public class TrainerTest {
    Trainer beginnerTrainer; //0 Pokemon, 0 teams

    Trainer emptyTeamTrainer; //0 Pokemon, 1 empty team

    Trainer onePokemonTrainer; //1 Pokemon, 0 teams
    Pokemon caterpie;

    Trainer intermediateTrainer; //1 Pokemon, 1 team with 1 Pokemon
    Pokemon kakuna;

    Trainer advancedTrainer; //3 Pokemon, 1 team with 1 Pokemon, 1 team with 2 Pokemon
    Pokemon beedrill, raticate, spearow;

    Trainer masterTrainer; //9 Pokemon, 3 teams with 3 Pokemon, 1 team with 6 Pokemon
    Pokemon bulbasaur, ivysaur, venusaur, charmander, charmeleon, charizard, squirtle, wartortle, blastoise;

    @BeforeEach
    void setup() {
        beginnerTrainer = new Trainer("Beginner Boy", MALE);

        emptyTeamTrainer = new Trainer("Empty Team Other", OTHER);
        emptyTeamTrainer.makeTeam("Empty Team");

        onePokemonTrainer = new Trainer("One Pokemon Girl", FEMALE);
        caterpie = new Pokemon(CATERPIE);
        onePokemonTrainer.addPokemonToRanch(caterpie);

        intermediateTrainer = new Trainer("Intermediate Boy", MALE);
        kakuna = new Pokemon(KAKUNA);
        intermediateTrainer.addPokemonToRanch(kakuna);
        intermediateTrainer.makeTeam("Intermediate Team");
        intermediateTrainer.addPokemonToTeam(kakuna, "Intermediate Team");


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
    void testSetName() {
        assertEquals("Beginner Boy", beginnerTrainer.getName());
        beginnerTrainer.setName("Jimmy");
        assertEquals("Jimmy", beginnerTrainer.getName());
    }

    @Test
    void testSetGender() {
        assertEquals(MALE, masterTrainer.getGender());
        masterTrainer.setGender(OTHER);
        assertEquals(OTHER, masterTrainer.getGender());
    }

    @Test
    void testNumberPokemon() {
        assertEquals(0, beginnerTrainer.numberPokemon());
        assertEquals(0, emptyTeamTrainer.numberPokemon());
        assertEquals(1, onePokemonTrainer.numberPokemon());
        assertEquals(3, advancedTrainer.numberPokemon());
        assertEquals(9, masterTrainer.numberPokemon());
    }

    @Test
    void testHasZeroPokemon() {
        assertTrue(beginnerTrainer.hasZeroPokemon());
        assertTrue(emptyTeamTrainer.hasZeroPokemon());
        assertFalse(onePokemonTrainer.hasZeroPokemon());
        assertFalse(advancedTrainer.hasZeroPokemon());
    }

    @Test
    void testGetAllPokemonNicknames() {
        assertEquals(new ArrayList<String>(), beginnerTrainer.getAllPokemonNicknames());
        ArrayList<String> nicknames = new ArrayList<>();
        nicknames.add("Beedrill");
        nicknames.add("Raticate");
        nicknames.add("Spearow");
        assertEquals(nicknames, advancedTrainer.getAllPokemonNicknames());
    }

    @Test
    void testGetPokemonFromNickname() {
        assertNull(beginnerTrainer.getPokemonFromNickname("poketman"));
        assertNull(masterTrainer.getPokemonFromNickname("Caterpie"));
        assertEquals(caterpie, onePokemonTrainer.getPokemonFromNickname("Caterpie"));
        assertEquals(beedrill, advancedTrainer.getPokemonFromNickname("Beedrill"));
        assertEquals(bulbasaur, masterTrainer.getPokemonFromNickname("Bulbasaur"));
    }

    @Test
    void testAddPokemonToRanch() {
        Pokemon testP = new Pokemon(PIKACHU, "Mousie", false, Pokemon.Gender.MALE);
        beginnerTrainer.addPokemonToRanch(testP);
        assertEquals(1, beginnerTrainer.numberPokemon());
        String newRanchString = "You have 1 Pokemon:\n" +
                "- Mousie the Pikachu";
        assertEquals(newRanchString, beginnerTrainer.displayRanch());
    }

    @Test
    void testDeletePokemonOnePokemonTrainer() {
        assertTrue(onePokemonTrainer.deletePokemon("Caterpie"));
        assertEquals(0, onePokemonTrainer.numberPokemon());
        assertEquals("You have 0 Pokemon:", onePokemonTrainer.displayRanch());
        assertEquals("You have 0 teams:", onePokemonTrainer.displayTeams());
    }

    @Test
    void testDeletePokemonAdvancedTrainer() {
        assertTrue(advancedTrainer.deletePokemon("Raticate"));

        String newRanchString = "You have 2 Pokemon:\n" +
                "- Beedrill the Beedrill\n" +
                "- Spearow the Spearow";
        String newTeamsString = "You have 2 teams:\n" +
                "- Advanced Team A (1 Pokemon)\n" +
                "- Advanced Team B (1 Pokemon)";
        assertEquals(newRanchString, advancedTrainer.displayRanch());
        assertEquals(newTeamsString, advancedTrainer.displayTeams());
    }

    @Test
    void testDeletePokemonNoPokemon() {
        assertEquals("You have 0 Pokemon:", beginnerTrainer.displayRanch());
        assertEquals("You have 0 teams:", beginnerTrainer.displayTeams());
        assertFalse(beginnerTrainer.deletePokemon("Pikachu"));
        assertEquals("You have 0 Pokemon:", beginnerTrainer.displayRanch());
        assertEquals("You have 0 teams:", beginnerTrainer.displayTeams());
    }

    @Test
    void testDisplayRanch() {
        assertEquals("You have 0 Pokemon:", beginnerTrainer.displayRanch());
        String ranch1 = "You have 1 Pokemon:\n" +
                "- Caterpie the Caterpie";
        assertEquals(ranch1, onePokemonTrainer.displayRanch());
        String ranch2 = "You have 3 Pokemon:\n" +
                "- Beedrill the Beedrill\n" +
                "- Raticate the Raticate\n" +
                "- Spearow the Spearow";
        assertEquals(ranch2, advancedTrainer.displayRanch());
    }

    @Test
    void testDetailedRanchSummary() {
        assertEquals("", beginnerTrainer.detailedRanchSummary());
        String summary1 = "\n" + caterpie.oneLineSummary(8, 8);
        assertEquals(summary1, onePokemonTrainer.detailedRanchSummary());
        String summary2 = "\n" + beedrill.oneLineSummary(8, 8);
        summary2 += "\n" + raticate.oneLineSummary(8, 8);
        summary2 += "\n" + spearow.oneLineSummary(8, 8);
        assertEquals(summary2, advancedTrainer.detailedRanchSummary());
    }

    @Test
    void testNumberTeams() {
        assertEquals(0, beginnerTrainer.numberTeams());
        assertEquals(1, emptyTeamTrainer.numberTeams());
        assertEquals(0, onePokemonTrainer.numberTeams());
        assertEquals(2, advancedTrainer.numberTeams());
        assertEquals(4, masterTrainer.numberTeams());
    }

    @Test
    void testHasZeroTeams() {
        assertTrue(beginnerTrainer.hasZeroTeams());
        assertFalse(emptyTeamTrainer.hasZeroTeams());
        assertTrue(onePokemonTrainer.hasZeroTeams());
        assertFalse(advancedTrainer.hasZeroTeams());
    }

    @Test
    void testGetTeamFromNameNotInTeam() {
        assertNull(beginnerTrainer.getTeamFromName("fancy team"));
        assertNull(masterTrainer.getTeamFromName("masterful"));
    }

    @Test
    void testGetAllTeamNames() {

    }

    @Test
    void testMakeTeam() {
        assertEquals("You have 0 teams:", beginnerTrainer.displayTeams());
        beginnerTrainer.makeTeam("Epic Team");
        String newTeamsString = "You have 1 team:\n" +
                "- Epic Team (0 Pokemon)";
        assertEquals(newTeamsString, beginnerTrainer.displayTeams());
    }

    @Test
    void testAddPokemonToTeamWrongTeamName() {
        Pokemon testP = new Pokemon(PIKACHU);
        assertFalse(masterTrainer.addPokemonToTeam(testP, "Fizz"));
    }

    @Test
    void testAddPokemonToTeamAlreadyInTeam() {
        assertFalse(advancedTrainer.addPokemonToTeam(beedrill, "Advanced Team A"));
    }

    @Test
    void testDeleteTeam() {
        assertTrue(masterTrainer.deleteTeam("Master Water Team"));
        String newTeamsString = "You have 3 teams:\n" +
                "- Master Grass Team (3 Pokemon)\n" +
                "- Master Fire Team (3 Pokemon)\n" +
                "- Grandmaster Team (6 Pokemon)";
        assertEquals(3, masterTrainer.numberTeams());
        assertEquals(newTeamsString, masterTrainer.displayTeams());
    }

    @Test
    void testDeleteTeamWrongTeamName() {
        assertFalse(masterTrainer.deleteTeam("Buzz"));
    }

    @Test
    void testDisplayTeams() {
        assertEquals("You have 0 teams:", beginnerTrainer.displayTeams());
        String team1 = "You have 1 team:\n" +
                "- Empty Team (0 Pokemon)";
        assertEquals(team1, emptyTeamTrainer.displayTeams());
        String team2 = "You have 2 teams:\n" +
                "- Advanced Team A (1 Pokemon)\n" +
                "- Advanced Team B (2 Pokemon)";
        assertEquals(team2, advancedTrainer.displayTeams());
    }

    @Test
    void testDetailedListOfTeamsSummary() {
        assertEquals("", beginnerTrainer.detailedListOfTeamsSummary());
        assertEquals("\n Empty Team is empty", emptyTeamTrainer.detailedListOfTeamsSummary());
        String lotSummary = "\nAdvanced Team A:  (1) Beedrill the Beedrill";
        lotSummary += "\nAdvanced Team B:  (1) Raticate the Raticate (2) Spearow the Spearow";
        assertEquals(lotSummary, advancedTrainer.detailedListOfTeamsSummary());
    }

    @Test
    void testToStringBeginnerTrainer() {
        String str = "Beginner Boy the Trainer!" +
                "\n- Gender: MALE" +
                "\n- Pokemon caught: 0" +
                "\n- Teams created: 0";
        assertEquals(str, beginnerTrainer.toString());
    }

    @Test
    void testToStringAdvancedTrainer() {
        String str = "Advanced Girl the Trainer!" +
                "\n- Gender: FEMALE" +
                "\n- Pokemon caught: 3" +
                "\n- Teams created: 2";
        assertEquals(str, advancedTrainer.toString());
    }
}
