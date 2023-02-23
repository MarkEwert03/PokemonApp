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
        System.out.println("- p -> modify one of your Pokemon");
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
            case "p":
                pickPokemonToModify();
                break;
            case "t":
                modifyTeam();
                break;
            default:
                System.out.println("Selection not valid, please try again");
                break;
        }
    }
    //Main Menu---------------------------------------------------------------------------------------------------------

    // MODIFIES: this
    // EFFECTS: allows user to change trainers name, and gender
    private void modifyTrainer() {
        System.out.println("You can change your trainer information here! Select from:");
        System.out.println("- n -> change your name");
        System.out.println("- g -> change your gender");
        System.out.println("- z -> return to the main menu");

        String command = input.next();
        processTrainerCommand(command);

        if (!command.equals("z")) {
            modifyTrainer();
        }
    }

    // EFFECTS: processes the users input for the Trainer submenu
    private void processTrainerCommand(String command) {
        switch (command) {
            case "n":
                changeTrainerName();
                break;
            case "g":
                changeTrainerGender();
                break;
            case "z":
                break;
            default:
                System.out.println("Selection not valid, please try again");
                break;
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
    //Main Menu - Trainer-----------------------------------------------------------------------------------------------

    // MODIFIES: this
    // EFFECTS: allows user to view, add, and delete Pokemon from the ranch and modify specific ones
    private void modifyRanch() {
        System.out.println("You can modify your ranch full of Pokemon here! Select from the options below:");
        System.out.println("- v -> view the current state of your ranch");
        System.out.println("- s -> show a detailed summary about all the Pokemon in your ranch");
        System.out.println("- a -> add a new Pokemon");
        System.out.println("- d -> delete a Pokemon (deletes from all teams too)");
        System.out.println("- z -> return to the main menu");

        String command = input.next();
        processRanchCommand(command);

        if (!command.equals("z")) {
            modifyRanch();
        }
    }

    // EFFECTS: processes the users command from the Ranch submenu
    private void processRanchCommand(String command) {
        switch (command) {
            case "v":
                System.out.println(myTrainer.displayRanch() + "\n");
                break;
            case "s":
                pokemonInRanchSummary();
                break;
            case "a":
                addNewPokemonToRanch();
                break;
            case "d":
                deletePokemonEverywhere();
            case "z":
                break;
            default:
                System.out.println("Selection not valid, please try again");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to add new Pokemon to ranch
    private void addNewPokemonToRanch() {
        System.out.println("Which Pokemon would you like to make? (type the species below)");
        String pokeSpecies = input.next();
        while (!Pokemon.isValidSpecies(pokeSpecies)) {
            System.out.println("Invalid species, please try again");
            pokeSpecies = input.next();
        }

        System.out.println("What nickname do you want your " + pokeSpecies.toUpperCase()
                + " to have? (It cannot be the exact same as any other Pokemon in your ranch)");
        String pokeNickname = input.next();
        while (myTrainer.getAllPokemonNicknames().contains(pokeNickname)) {
            System.out.println("Duplicate nickname, please try again");
            pokeNickname = input.next();
        }

        myTrainer.addPokemonToRanch(new Pokemon(Pokemon.Species.valueOf(pokeSpecies.toUpperCase()), pokeNickname,
                false, Pokemon.Gender.MALE));
    }

    // EFFECTS: allows user to choose a specific Pokemon to get a specific summary about
    private void pokemonInRanchSummary() {
        if (myTrainer.hasZeroPokemon()) {
            System.out.println("Your ranch is empty! Make a Pokemon first!");
        } else {
            System.out.println(myTrainer.detailedRanchSummary(myTrainer.getLongestSpeciesName(),
                    myTrainer.getLongestNickname()));
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to delete a specific Pokemon from both ranch and all teams
    private void deletePokemonEverywhere() {
        if (myTrainer.hasZeroPokemon()) {
            System.out.println("You can't delete a Pokemon if you don't have any!");
        } else {
            System.out.println("Which Pokemon would you like to delete? (type nickname)");
            Pokemon p = getUserPokemon();
            System.out.println("Are you sure? This action can not be undone... Please confirm Y/N");
            String confirm = input.next().toUpperCase();
            while (!confirm.equals("Y") && !confirm.equals("N")) {
                System.out.println("Invalid conformation, please try again.");
                confirm = input.next().toUpperCase();
            }
            if (confirm.equals("Y")) {
                myTrainer.deletePokemon(p.getNickname());
            }
        }
    }
    //Main Menu - Ranch ------------------------------------------------------------------------------------------------

    // EFFECTS: allows user to pick the Pokemon they wish to modify
    private void pickPokemonToModify() {
        if (myTrainer.hasZeroPokemon()) {
            System.out.println("Your ranch is empty! Make a Pokemon first!");
        } else {
            System.out.println("Which Pokemon would you like to modify? (type nickname)");
            Pokemon p = getUserPokemon();
            modifyPokemon(p);
        }
    }

    // MODIFIES: this
    // EFFECTS: allows the user to change attributes for a specific Pokemon
    private void modifyPokemon(Pokemon p) {
        System.out.println("What attributes would you like to change about " + p.toString()
                + "? Select from:");
        System.out.println("- o -> get an overview about this Pokemon");
        System.out.println("- n -> change nickname");
        System.out.println("- g -> change gender");
        System.out.println("- s -> switch shiny status");
        System.out.println("- z -> return to the main menu");

        String command = input.next();
        processPokemonCommand(command, p);

        if (!command.equals("z")) {
            modifyPokemon(p);
        }
    }

    // EFFECTS: processes the users command from the Pokemon submenu
    private void processPokemonCommand(String command, Pokemon p) {
        switch (command) {
            case "o":
                System.out.println(p.summary() + "\n");
                break;
            case "n":
                changePokemonNickname(p);
                break;
            case "g":
                changePokemonGender(p);
                break;
            case "s":
                switchPokemonShinyStatus(p);
                break;
            case "z":
                break;
            default:
                System.out.println("Selection not valid, please try again");
                break;
        }
    }

    // EFFECTS: allows user to change the nickname of p
    private void changePokemonNickname(Pokemon p) {
        System.out.println("What would you like to change " + p.getNickname() + "\'s nickname to?");
        p.setNickname(input.next());
    }

    // EFFECTS: allows user to change the gender of p
    private void changePokemonGender(Pokemon p) {
        System.out.println(p.getNickname() + "\'s current gender is " + p.getGender()
                + ". What do you want to change " + p + "\'s gender to? Select from:");
        System.out.println("- m -> male");
        System.out.println("- f -> female");
        System.out.println("- u -> unknown");
        String in = input.next().toLowerCase();
        Pokemon.Gender m = Pokemon.Gender.MALE;
        Pokemon.Gender f = Pokemon.Gender.FEMALE;
        Pokemon.Gender u = Pokemon.Gender.UNKNOWN;
        Pokemon.Gender newGender = (in.equals("m")) ? m : (in.equals("f")) ? f : u;
        p.setGender(newGender);
    }

    // EFFECTS: allows user to change the shiny status of p
    private void switchPokemonShinyStatus(Pokemon p) {
        p.setShiny(!p.getShiny());
        String shinyText = p.getShiny() ? "⭐shiny⭐" : "not shiny";
        System.out.println(p.getNickname() + " is now " + shinyText);
    }

    //Main Menu - Pokemon-----------------------------------------------------------------------------------------------

    // MODIFIES: this
    // EFFECTS: allows user to create & delete teams, and & modify specific ones
    private void modifyTeam() {
        //!!!
    }
    //Main Menu - Team -------------------------------------------------------------------------------------------------

    // EFFECTS: helper method that asks user for a valid nickname, then returns Pokemon with that nickname
    private Pokemon getUserPokemon() {
        Pokemon userPokemon = null;
        System.out.println(myTrainer.displayRanch());
        String pokeNickname = input.next();
        while (!myTrainer.getAllPokemonNicknames().contains(pokeNickname)) {
            System.out.println("Invalid nickname, please try again");
            pokeNickname = input.next();
        }
        userPokemon = myTrainer.getPokemonFromNickname(pokeNickname);
        return userPokemon;
    }
    //Helpers ----------------------------------------------------------------------------------------------------------
}
