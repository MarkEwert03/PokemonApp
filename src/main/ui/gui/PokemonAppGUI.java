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

// represents the main frame for the gui version of the Pokemon app
public class PokemonAppGUI extends JFrame implements ActionListener {
    // CONSTANTS
    private static final String JSON_STORE = "./data/trainer.json";
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;

    // Colours
    private final Color paleRed = new Color(0xfad1d1);
    private final Color paleOrange = new Color(0xfae6d1);
    private final Color paleYellow = new Color(0xfafad1);
    private final Color paleLime = new Color(0xe6fad1);
    private final Color paleGreen = new Color(0xd1fad1);
    private final Color paleMint = new Color(0xd1fae6);
    private final Color paleCyan = new Color(0xd1fafa);
    private final Color paleBlue = new Color(0xd1e6fa);
    private final Color palePurple = new Color(0xd1d1fa);
    private final Color paleViolet = new Color(0xe6d1fa);
    private final Color palePink = new Color(0xfad1fa);
    private final Color paleSalmon = new Color(0xfad1e6);
    private final Color paleGrey = new Color(0xcccccc);

    // Miscellaneous
    private Trainer myTrainer;

    // Main tabbed main things
    private JTabbedPane mainTabbedPane = new JTabbedPane();
    private JPanel mainMenuPanel = new JPanel();
    private JPanel trainerPanel = new JPanel();
    private JPanel ranchPanel = new JPanel();
    private JPanel pokemonPanel = new JPanel();
    private JPanel listOfTeamsPanel = new JPanel();
    private JPanel teamPanel = new JPanel();

    // JPanels
    private JPanel imagePanel = new JPanel();

    // JSON
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: default constructor that runs the GUI program and makes main frame
    public PokemonAppGUI() throws FileNotFoundException {
        super();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        mainMenuFrame();
    }

    // MODIFIES: this
    // EFFECTS: creates the main menu frame that displays options to modify trainer information, ranch, specific
    //          Pokemon, list of teams, specific teams, save & load data, and quit
    private void mainMenuFrame() {
        createImage();

        this.setTitle("Pokemon GUI App");
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new GridLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainTabbedPane);

        setupTab("Main Menu", mainMenuPanel, paleGrey);
        setupTab("Modify Trainer Information", trainerPanel, paleRed);
        setupTab("Modify Ranch of Pokemon", ranchPanel, paleYellow);
        setupTab("Modify a Specific Pokemon", pokemonPanel, paleGreen);
        setupTab("Modify List of Teams", listOfTeamsPanel, paleCyan);
        setupTab("Modify a Specific Team", teamPanel, palePurple);

        mainMenuPanel.add(imagePanel);
    }

    // EFFECTS: helper that adds panel to mainTabbedPane and picks tab and panel colour for given c
    private void setupTab(String text, JPanel panel, Color c) {
        mainTabbedPane.add(text, panel);
        mainTabbedPane.setBackgroundAt(mainTabbedPane.indexOfTab(text), c);
        panel.setBackground(c);
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

    // MODIFIES: this
    // EFFECTS:
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "trainer":
                break;
            case "ranch":
                break;
            case "pokemon":
                break;
            case "list of teams":
                break;
            case "team":
                break;
        }
    }
}
