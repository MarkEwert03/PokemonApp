package ui.gui.panels;

import javax.swing.*;
import java.awt.*;

// represents a JPanel for users to modify their trainer information and see a sprite of themselves
public class TrainerPanel extends ColorPanel {
    private JPanel trainerSpritePanel;

    // EFFECTS: constructs a new trainer panel with given colour
    public TrainerPanel(Color color) {
        super(color);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for trainer panel
    @Override
    protected void initialize() {
        trainerSpritePanel = new JPanel();
        ImageIcon maleTrainer = new ImageIcon("./data/sprites/male trainer.png");
        trainerSpritePanel.add(new JLabel(maleTrainer));
        trainerSpritePanel.setVisible(true);
        ImageIcon femaleTrainer = new ImageIcon("./data/sprites/female trainer.png");
        trainerSpritePanel.add(new JLabel(femaleTrainer));
        trainerSpritePanel.setVisible(true);
        this.add(trainerSpritePanel);
    }
}
