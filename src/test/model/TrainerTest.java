package model;

import org.junit.jupiter.api.BeforeEach;

import static model.Trainer.Gender.*;
import static model.Pokemon.Species.*;

public class TrainerTest {
    Trainer beginnerTrainer; //0 Pokemon, 0 teams

    Trainer emptyTeamTrainer; //0 Pokemon, 1 empty team
    Team emptyTeam;

    Trainer onePokemonTrainer; //1 Pokemon, 0 teams
    Pokemon caterpie;

    Trainer intermediateTrainer; //1 Pokemon, 1 team with 1 Pokemon
    Pokemon kakuna;
    Team intermediateTeam;

    Trainer advancedTrainer; //3 Pokemon, 1 team with 1 Pokemon, 1 team with 2 Pokemon
    Pokemon beedrill, raticate, spearow;
    Team advancedTeam1, advancedTeam2;

    Trainer masterTrainer; //9 Pokemon, 3 teams with 3 Pokemon, 1 team with 6 Pokemon
    Pokemon bulbasaur, ivysaur, venusaur, charmander, charmeleon, charizard, squirtle, wartortle, blastoise;
    Team masterTeam1, masterTeam2, masterTeam3, masterTeamFull;

    @BeforeEach
    void setup() {
        beginnerTrainer = new Trainer("Beginner Boy", MALE);

        emptyTeamTrainer = new Trainer("Empty Team Other", OTHER);
        emptyTeam = new Team("Empty Team");

        onePokemonTrainer = new Trainer("One Pokemon Girl", FEMALE);
        caterpie = new Pokemon(CATERPIE);

        intermediateTrainer = new Trainer("Intermediate Boy", MALE);
        kakuna = new Pokemon(KAKUNA);
        intermediateTeam = new Team("Intermediate Team");

        advancedTrainer = new Trainer("Advanced Girl", FEMALE);
        beedrill = new Pokemon(BEEDRILL);
        raticate = new Pokemon(RATICATE);
        spearow = new Pokemon(SPEAROW);
        advancedTeam1 = new Team("Advanced Team 1");
        advancedTeam2 = new Team("Advanced Team 2");

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
        masterTeam1 = new Team("Master Grass Team");
        masterTeam2 = new Team("Master Fire Team");
        masterTeam3 = new Team("Master Water Team");
        masterTeamFull = new Team("Grandmaster Team");
    }
}
