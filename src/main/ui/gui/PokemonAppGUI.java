package ui.gui;

import model.Trainer;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents the gui version of the Pokemon app
public class PokemonAppGUI implements ActionListener {
    // CONSTANTS
    private static final String JSON_STORE = "./data/trainer.json";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 450;

    // Miscellaneous
    private Trainer myTrainer;
    int count = 0;

    // Main Menu things
    private JFrame mainFrame = new JFrame();
    JPanel mainMenuPanel = new JPanel();
    private JButton trainerButton = new JButton("Modify Trainer Information");
    private JButton ranchButton = new JButton("Modify Ranch of Pokemon");
    private JButton pokemonButton = new JButton("Modify a Specific Pokemon");
    private JButton listOfTeamsButton = new JButton("Modify List of Teams");
    private JButton teamButton = new JButton("Modify a Specific Team");
    private JButton saveInfoPAnel = new JButton("Save Information");
    private JButton loadInfoButton = new JButton("Load Information");

    // JPanels
    private JPanel testingPanel = new JPanel();
    private JPanel imagePanel = new JPanel();

    // JSON
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: default constructor that runs the GUI program
    public PokemonAppGUI() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        mainMenuFrame();
    }

    // MODIFIES: this
    // EFFECTS: creates the main menu frame that displays options to modify trainer information, ranch, specific
    //          Pokemon, list of teams, specific teams, save & load data, and quit
    private void mainMenuFrame() {
        createImage();

        mainFrame.setTitle("Pokemon GUI App");
        mainFrame.setSize(960, 540);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainMenuPanel.setBounds(480, 270, 50, 50);
        setupNewButton(trainerButton, "trainer", new Color(0xfad1d1));
        setupNewButton(ranchButton, "ranch", new Color(0xfafad1));
        setupNewButton(pokemonButton, "pokemon", new Color(0xd1fad1));
        setupNewButton(listOfTeamsButton, "list of teams", new Color(0xd1fafa));
        setupNewButton(teamButton, "team", new Color(0xd1d1fa));

        mainFrame.add(mainMenuPanel);
        mainFrame.add(imagePanel);
    }

    // EFFECTS: helper that initializes given button with given action command, color, and default behaviour
    private void setupNewButton(JButton button, String command, Color c) {
        button.setActionCommand(command);
        button.addActionListener(this);
        button.setVisible(true);
        button.setBackground(c);
        mainMenuPanel.add(button);
    }

    // EFFECTS: creates a new image of mew from file
    private void createImage() {
        ImageIcon mewIcon = new ImageIcon("./data/mew.png");
        imagePanel.add(new JLabel(mewIcon));
        imagePanel.setVisible(true);
    }

    //SAVE/LOAD --------------------------------------------------------------------------------------------------------
    // EFFECTS: saves the trainer to file
    private void saveTrainer() {
        try {
            jsonWriter.open();
            jsonWriter.write(myTrainer);
            jsonWriter.close();
            System.out.println("Saved " + myTrainer.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: opens and loads trainer from file
    private void openTrainer() {
        try {
            myTrainer = jsonReader.read();
            System.out.println("Loaded " + myTrainer.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    //SAVE/LOAD --------------------------------------------------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        //
    }
}
