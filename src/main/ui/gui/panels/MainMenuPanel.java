package ui.gui.panels;

import model.Pokemon;
import model.Trainer;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.PokemonAppGUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// represents a JPanel for the main menu for users to save and load data
public class MainMenuPanel extends ColorPanel {
    private JPanel mewPanel;
    private JButton saveButton;
    private JButton loadButton;

    // JSON
    private PokemonAppGUI window;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/trainer.json";

    // EFFECTS: constructs a new main menu panel with given colour
    public MainMenuPanel(Color color, Trainer globalTrainer, PokemonAppGUI pag) {
        super(color, globalTrainer);
        window = pag;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for main menu panel
    @Override
    protected void initialize() {
        saveButton = new JButton("Save Information");
        saveButton.setVisible(true);
        this.add(saveButton);
        saveButton.addActionListener(e -> {
            saveInformation();
        });

        loadButton = new JButton("Load Information");
        loadButton.setVisible(true);
        this.add(loadButton);
        loadButton.addActionListener(e -> {
            loadInformation();
        });

        mewPanel = new JPanel();
        ImageIcon mewIcon = new ImageIcon("./data/sprites/mew.png");
        mewPanel.add(new JLabel(mewIcon));
        mewPanel.setVisible(true);
        this.add(mewPanel);
    }

    // EFFECTS: writes trainer data to json file
    public void saveInformation() {
        try {
            jsonWriter.open();
            jsonWriter.write(myTrainer);
            jsonWriter.close();
            System.out.println("Saved " + myTrainer.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: reads trainer data to json file
    public void loadInformation() {
        try {
            myTrainer = jsonReader.read();
            window.setGlobalTrainer(myTrainer);
            System.out.println("Loaded " + myTrainer.getName() + " from " + JSON_STORE);
            System.out.println(myTrainer);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: nothing
    @Override
    public void updateLabels() {
        // nothing needs to update on main menu
    }
}
