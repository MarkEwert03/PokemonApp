package ui;

import model.Pokemon;
import ui.gui.PokemonAppGUI;

import java.io.FileNotFoundException;


// represents the main class that the Pokemon application will be run from
public class Main {
    public static void main(String[] args) {
        // GUI
        try {
            new PokemonAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

        // Console Based
//        try {
//            new PokemonApp();
//        } catch(
//                FileNotFoundException e)   {
//            System.out.println("Unable to run application: file not found");
//        }
    }
}
