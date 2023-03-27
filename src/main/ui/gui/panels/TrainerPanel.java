package ui.gui.panels;

import javax.swing.*;
import java.awt.*;

// represents a JPanel for users to modify their trainer information and see a sprite of themselves
public class TrainerPanel extends ColorPanel {
    private JPanel spritePanel;

    private JPanel infoPanel;

    private JPanel namePanel;
    private JLabel nameLabel;
    private JTextField nameTextField;

    private JPanel genderPanel;
    private JLabel genderLabel;

    private JButton confirmButton;

    private JLabel descriptionLabel;

    // EFFECTS: constructs a new trainer panel with given colour
    public TrainerPanel(Color color) {
        super(color);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for trainer panel
    @Override
    protected void initialize() {
        this.setLayout(new GridLayout(1, 2));

        spritePanel = new JPanel();
        this.add(spritePanel);
        spritePanel.setBackground(colour);
        ImageIcon maleTrainer = new ImageIcon("./data/sprites/male trainer.png");
        spritePanel.add(new JLabel(maleTrainer));
        spritePanel.setVisible(true);

        infoPanel = new JPanel();
        this.add(infoPanel);
        infoPanel.setLayout(new GridLayout(4, 1));
        infoPanel.setBackground(colour);

        setupInfoPanel();

    }

    // EFFECTS: creates name panel, gender panel, confirm button, and info text needed for trainer panel
    private void setupInfoPanel() {
        namePanel = new JPanel();
        infoPanel.add(namePanel);
        namePanel.setBackground(colour);
        nameLabel = new JLabel("Trainer name:");
        namePanel.add(nameLabel);
        nameTextField = new JTextField(10);
        namePanel.add(nameTextField);

        genderPanel = new JPanel();
        infoPanel.add(genderPanel);
        genderPanel.setBackground(colour);
        genderLabel = new JLabel("Trainer Gender:");
        genderPanel.add(genderLabel);

        confirmButton = new JButton("Confirm Changes");
        infoPanel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            handleConfirmTrainerName();
        });

        descriptionLabel = new JLabel(newLineStringToMultilineLabel(myTrainer.toString()));
        infoPanel.add(descriptionLabel);
    }

    // EFFECTS: handles confirm button presses to the change trainer name button
    private void handleConfirmTrainerName() {
        String userTrainerName = nameTextField.getText();
        if (!userTrainerName.isBlank()) {
            myTrainer.setName(userTrainerName);
            updateLabels();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Trainer Name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: updates text label for trainer information
    @Override
    public void updateLabels() {
        descriptionLabel.setText(newLineStringToMultilineLabel(myTrainer.toString()));
    }
}
