package ui.gui.panels;

import javax.swing.*;
import java.awt.*;

// represents a JPanel for the main menu for users to save and load data
public class MainMenuPanel extends ColorPanel {
    private JPanel mewPanel;

    // EFFECTS: constructs a new main menu panel with given colour
    public MainMenuPanel(Color color) {
        super(color);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for main menu panel
    @Override
    protected void initialize() {
        mewPanel = new JPanel();
        ImageIcon mewIcon = new ImageIcon("./data/sprites/mew.png");
        mewPanel.add(new JLabel(mewIcon));
        mewPanel.setVisible(true);
        this.add(mewPanel);
    }
}
