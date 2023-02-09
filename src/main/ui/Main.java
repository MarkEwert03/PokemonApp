package ui;

import model.Pokemon;
import model.Team;
import model.Trainer;

public class Main {
    public static void main(String[] args) {
        Trainer mark = new Trainer("Mark", Trainer.Gender.MALE);
        System.out.println(mark);

        Pokemon jenny = new Pokemon(Pokemon.Species.PIKACHU, "Jenny", false, Pokemon.Gender.FEMALE);
        System.out.println(jenny);

        Pokemon sean = new Pokemon(Pokemon.Species.CHARMANDER, "Sean", false, Pokemon.Gender.MALE);
        System.out.println(sean);

        Team myTeam = new Team("My Team");
    }
}
