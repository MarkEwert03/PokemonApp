package ui;

import model.*;

import java.util.*;

// Pokemon team builder application
public class PokemonApp {
    private Trainer myTrainer;
    private Scanner input;

    // EFFECTS: runs the Pokemon app
    public PokemonApp() {
        runPokemon();
    }

    // MODIFIES: this
    // EFFECTS: processes the users input
    private void runPokemon() {
        boolean keepGoing = true;
        String command = null;

        System.out.println("\nWelcome Trainer! In this program you will be able to create your very own dream team "
                + "of Pokemon!");

        init();

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes the Trainer
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        System.out.println("First, can you please tell me your name? (Type below then hit ENTER)");
        String trainerName = input.next();

        myTrainer = new Trainer(trainerName, Trainer.Gender.OTHER);

        System.out.println("\nPerfect! Nice to meet you " + trainerName
                + "! Have fun making Pokemon teams!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMainMenu() {
        System.out.println("\nSelect from:");
        System.out.println("- d -> display your information");
        System.out.println("- i -> modify your trainer information");
        System.out.println("- r -> customize your ranch of Pokemon");
        System.out.println("- t -> customize your Pokemon Teams");
        System.out.println("- q -> quit the application");
    }

    // MODIFIES: this
    // EFFECTS: processes the users command
    private void processCommand(String command) {
        switch (command) {
            case "d":
                System.out.println(myTrainer.toString());
                break;
            case "i":
                modifyTrainer();
                break;
            case "r":
                modifyRanch();
                break;
            case "t":
                modifyTeam();
                break;
            default:
                System.out.println("Selection not valid, please try again");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to change trainers name, and gender
    private void modifyTrainer() {
        System.out.println("You can change your trainer information here! Select from:");
        System.out.println("- n -> change your name");
        System.out.println("- g -> change your gender");
        System.out.println("- z -> go back to the main menu");

        String command = input.next();
        switch (command) {
            case "n":
                changeTrainerName();
                break;
            case "g":
                changeTrainerGender();
                break;
            default:
                System.out.println("Selection not valid, please try again");
                break;
        }

        if (!command.equals("z")) {
            modifyTrainer();
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to change their Trainer's name
    private void changeTrainerName() {
        System.out.println("Your current name is " + myTrainer.getName()
                + ". What do you want to change your name to?");
        String newName = input.next();
        myTrainer.setName(newName);
    }

    // MODIFIES: this
    // EFFECTS: allows user to change their Trainer's gender
    private void changeTrainerGender() {
        System.out.println("Your current gender is " + myTrainer.getGender()
                + ". What do you want to change your gender to? Select from:");
        System.out.println("- m -> male");
        System.out.println("- f -> female");
        System.out.println("- o -> other");
        String in = input.next().toLowerCase();
        Trainer.Gender m = Trainer.Gender.MALE;
        Trainer.Gender f = Trainer.Gender.FEMALE;
        Trainer.Gender o = Trainer.Gender.OTHER;
        Trainer.Gender newGender = (in.equals("m")) ? m : (in.equals("f")) ? f : o;
        myTrainer.setGender(newGender);
    }

    // MODIFIES: this
    // EFFECTS: allows user to view, add, and delete Pokemon from the ranch and modify specific ones
    private void modifyRanch() {
        //!!!
    }

    // MODIFIES: this
    // EFFECTS: allows user to create & delete teams, and & modify specific ones
    private void modifyTeam() {
        //!!!
    }
}
