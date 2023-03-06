package ui;

import model.Pokemon;
import model.Team;
import model.Trainer;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            PokemonApp pa = new PokemonApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
