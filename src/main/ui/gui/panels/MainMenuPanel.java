package ui.gui.panels;

import model.Team;

import javax.swing.*;
import java.awt.*;

// represents a JPanel for the main menu for users to save and load data
public class MainMenuPanel extends ColorPanel {
    private JPanel mewPanel;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel trainerLabel;

    // EFFECTS: constructs a new main menu panel with given colour
    public MainMenuPanel(Color color) {
        super(color);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for main menu panel
    @Override
    protected void initialize() {
        saveButton = new JButton("Save Information");
        saveButton.setVisible(true);
        saveButton.setBackground(colour.darker());
        this.add(saveButton);

        loadButton = new JButton("Load Information");
        loadButton.setVisible(true);
        loadButton.setBackground(colour.darker());
        this.add(loadButton);

        mewPanel = new JPanel();
        ImageIcon mewIcon = new ImageIcon("./data/sprites/mew.png");
        mewPanel.add(new JLabel(mewIcon));
        mewPanel.setVisible(true);
        this.add(mewPanel);

        trainerLabel = new JLabel(newLineStringToMultilineLabel(myTrainer.toString()));
        this.add(trainerLabel);
    }

    // EFFECTS: updates text label for trainer information
    @Override
    public void updateLabels() {
        trainerLabel.setText(newLineStringToMultilineLabel(myTrainer.toString()));
    }
}
