package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static model.Trainer.Gender.*;
import static model.Pokemon.Species.*;


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
        advancedTrainer.makeTeam("Advanced Team 1");
        advancedTrainer.makeTeam("Advanced Team 2");
        advancedTrainer.addPokemonToTeam(beedrill, "Advanced Team 1");
        advancedTrainer.addPokemonToTeam(raticate, "Advanced Team 2");
        advancedTrainer.addPokemonToTeam(spearow, "Advanced Team 2");

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
    void testNumberPokemonBeginnerTrainer() {
        assertEquals(0, beginnerTrainer.numberPokemon());
    }
}
