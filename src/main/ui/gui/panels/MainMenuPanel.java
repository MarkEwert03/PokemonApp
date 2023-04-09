package ui.gui.panels;

import model.Event;
import model.EventLog;
import model.Trainer;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.PokemonAppGUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

// represents a JPanel for the main menu for users to save and load data
public class MainMenuPanel extends ColorPanel {
    // top row
    private JPanel introPanel;
    private JLabel introLabel;

    // middle row
    private JPanel statePanel;
    private JButton exitButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel changedStateLabel;

    // bottom row
    private JPanel mewPanel;

    // JSON
    private PokemonAppGUI window;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/trainer.json";

    // EFFECTS: constructs a new main menu panel with given colour and trainer
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
        this.setLayout(new GridLayout(3, 1));

        introPanel = new JPanel();
        this.add(introPanel);
        introPanel.setBackground(colour);
        introLabel = new JLabel("Pokemon Team Builder!");
        introPanel.add(introLabel);
        introLabel.setFont(new Font("Serif", Font.BOLD, 60));

        statePanel = new JPanel();
        this.add(statePanel);
        setupStatePanel();

        mewPanel = new JPanel();
        this.add(mewPanel);
        mewPanel.setBackground(colour);
        ImageIcon mewIcon = new ImageIcon("./data/sprites/mew.png");
        mewPanel.add(new JLabel(mewIcon));
        mewPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates save and load buttons needed for persistance
    private void setupStatePanel() {
        statePanel.setBackground(colour);

        exitButton = new JButton("Exit App");
        exitButton.setVisible(true);
        statePanel.add(exitButton);
        exitButton.addActionListener(e -> {
            handleExit();
        });

        saveButton = new JButton("Save Information");
        saveButton.setVisible(true);
        statePanel.add(saveButton);
        saveButton.addActionListener(e -> {
            saveInformation();
        });

        loadButton = new JButton("Load Information");
        loadButton.setVisible(true);
        statePanel.add(loadButton);
        loadButton.addActionListener(e -> {
            loadInformation();
        });

        changedStateLabel = new JLabel("");
        statePanel.add(changedStateLabel);
    }

    // EFFECTS: closes the app and prints out log to console
    private void handleExit() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event);
        }
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: writes trainer data to json file
    private void saveInformation() {
        try {
            jsonWriter.open();
            jsonWriter.write(myTrainer);
            jsonWriter.close();
            changedStateLabel.setText("Saved " + myTrainer.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: reads trainer data to json file
    private void loadInformation() {
        try {
            myTrainer = jsonReader.read();
            window.setGlobalTrainer(myTrainer);
            changedStateLabel.setText("Loaded " + myTrainer.getName() + " from " + JSON_STORE);
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
