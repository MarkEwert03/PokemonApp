package ui.gui;

import model.Trainer;
import ui.gui.panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

// represents the main frame for the gui version of the Pokemon app
public class PokemonAppGUI extends JFrame {
    // CONSTANTS
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 750;

    private Trainer globalTrainer;

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

    // Main tabbed main things
    private JTabbedPane mainTabbedPane = new JTabbedPane();
    private ColorPanel mainMenuPanel;
    private ColorPanel trainerPanel;
    private ColorPanel ranchPanel;
    private ColorPanel pokemonPanel;
    private ColorPanel listOfTeamsPanel;
    private ColorPanel teamPanel;

    // MODIFIES: this
    // EFFECTS: default constructor that runs the GUI program and makes main frame
    public PokemonAppGUI() throws FileNotFoundException {
        super();
        globalTrainer = new Trainer("Trainer");
        setupFrame();
    }

    // sets the global trainer (to be used after load)
    public void setGlobalTrainer(Trainer newTrainer) {
        globalTrainer = newTrainer;
    }

    // MODIFIES: this
    // EFFECTS: creates the main menu frame that displays options to modify trainer information, ranch, specific
    //          Pokemon, list of teams, specific teams, save & load data, and quit
    private void setupFrame() {
        mainMenuPanel = new MainMenuPanel(paleGrey, globalTrainer, this);
        trainerPanel = new TrainerPanel(paleRed, globalTrainer);
        ranchPanel = new RanchPanel(paleYellow, globalTrainer);
        pokemonPanel = new PokemonPanel(paleGreen, globalTrainer);
        listOfTeamsPanel = new ListOfTeamsPanel(paleCyan, globalTrainer);
        teamPanel = new TeamPanel(palePurple, globalTrainer);

        this.setTitle("Pokemon GUI App");
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new GridLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainTabbedPane);

        setupTab("Main Menu", mainMenuPanel);
        setupTab("Modify Trainer Information", trainerPanel);
        setupTab("Modify Ranch of Pokemon", ranchPanel);
        setupTab("Modify a Specific Pokemon", pokemonPanel);
        setupTab("Modify List of Teams", listOfTeamsPanel);
        setupTab("Modify a Specific Team", teamPanel);

        this.mainTabbedPane.addChangeListener(e -> {
            ColorPanel currentTab = (ColorPanel) mainTabbedPane.getSelectedComponent();
            currentTab.setMyTrainer(globalTrainer);
            currentTab.updateLabels();
        });
    }

    // EFFECTS: helper that adds panel to mainTabbedPane and picks tab and panel colour for given c
    private void setupTab(String text, ColorPanel panel) {
        mainTabbedPane.add(text, panel);
        mainTabbedPane.setBackgroundAt(mainTabbedPane.indexOfTab(text), panel.getColour());
        panel.setBackground(panel.getColour());
    }

}
