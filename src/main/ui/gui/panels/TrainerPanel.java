package ui.gui.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// represents a JPanel for users to modify their trainer information and see a sprite of themselves
public class TrainerPanel extends ColorPanel {
    private JPanel trainerSpritePanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JButton confirmButton;
    private JLabel trainerInfoLabel;

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
        this.add(trainerSpritePanel);

        nameLabel = new JLabel("Trainer name:");
        this.add(nameLabel);
        nameTextField = new JTextField(10);
        this.add(nameTextField);

        confirmButton = new JButton("Confirm Changes");
        confirmButton.addActionListener(e -> {
            handleConfirmTrainerName();
        });
        this.add(confirmButton);

        trainerInfoLabel = new JLabel(newLineStringToMultilineLabel(myTrainer.toString()));
        this.add(trainerInfoLabel);

    }

    // EFFECTS:
    private void handleConfirmTrainerName() {
        String text = nameTextField.getText();
        if (!text.isBlank()) {
            myTrainer.setName(text);
            updateLabels();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Trainer Name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: updates text label for trainer information
    @Override
    public void updateLabels() {
        trainerInfoLabel.setText(newLineStringToMultilineLabel(myTrainer.toString()));
    }
}
