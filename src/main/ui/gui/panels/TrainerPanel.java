package ui.gui.panels;

import model.Trainer;

import javax.swing.*;
import java.awt.*;

// represents a JPanel for users to modify their trainer information and see a sprite of themselves
public class TrainerPanel extends ColorPanel {
    private JPanel spritePanel;
    private JLabel spriteImageLabel;

    private JPanel infoPanel;

    private JPanel namePanel;
    private JLabel nameLabel;
    private JTextField nameTextField;

    private JPanel genderPanel;
    private JLabel genderLabel;
    private ButtonGroup genderButtonGroup;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton otherRadioButton;

    private JButton confirmButton;

    private JLabel descriptionLabel;

    // EFFECTS: constructs a new trainer panel with given colour
    public TrainerPanel(Color color, Trainer globalTrainer) {
        super(color, globalTrainer);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for trainer panel
    @Override
    protected void initialize() {
        this.setLayout(new GridLayout(1, 2));

        spritePanel = new JPanel();
        this.add(spritePanel);
        spritePanel.setBackground(colour);
        ImageIcon img = new ImageIcon("./data/sprites/male trainer.png");
        spriteImageLabel = new JLabel(img);
        spritePanel.add(spriteImageLabel);
        spritePanel.setVisible(true);

        infoPanel = new JPanel();
        this.add(infoPanel);
        infoPanel.setLayout(new GridLayout(4, 1));
        infoPanel.setBackground(colour);

        setupInfoPanel();
    }

    // MODIFIES: this
    // EFFECTS: creates name panel, gender panel, confirm button, and info text needed for trainer panel
    private void setupInfoPanel() {
        setupNamePanel();
        setupGenderPanel();

        confirmButton = new JButton("Confirm Changes");
        infoPanel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            handleConfirm();
        });

        descriptionLabel = new JLabel(newLineStringToMultilineLabel(myTrainer.toString()));
        infoPanel.add(descriptionLabel);
    }

    // MODIFIES: this
    // EFFECTS: sets up the label and text field needed for users to change their trainer's name
    private void setupNamePanel() {
        namePanel = new JPanel();
        infoPanel.add(namePanel);
        namePanel.setBackground(colour);
        nameLabel = new JLabel("Trainer name:");
        namePanel.add(nameLabel);
        nameTextField = new JTextField(10);
        namePanel.add(nameTextField);
        nameTextField.setText(myTrainer.getName());
    }

    // MODIFIES: this
    // EFFECTS: sets up up gender label and radio buttons in button group
    private void setupGenderPanel() {
        genderPanel = new JPanel();
        infoPanel.add(genderPanel);
        genderPanel.setBackground(colour);
        genderLabel = new JLabel("Trainer Gender:");
        genderPanel.add(genderLabel);

        genderButtonGroup = new ButtonGroup();
        maleRadioButton = new JRadioButton("Male");
        genderButtonGroup.add(maleRadioButton);
        genderPanel.add(maleRadioButton);

        femaleRadioButton = new JRadioButton("Female");
        genderButtonGroup.add(femaleRadioButton);
        genderPanel.add(femaleRadioButton);

        otherRadioButton = new JRadioButton("Other");
        genderPanel.add(otherRadioButton);
        genderButtonGroup.add(otherRadioButton);
    }

    // MODIFIES: this
    // EFFECTS: handles confirm button presses to the change trainer name button
    private void handleConfirm() {
        String userTrainerName = nameTextField.getText();
        if (!userTrainerName.isEmpty()) {
            myTrainer.setName(userTrainerName);
            if (maleRadioButton.isSelected()) {
                myTrainer.setGender(Trainer.Gender.MALE);
            } else if (femaleRadioButton.isSelected()) {
                myTrainer.setGender(Trainer.Gender.FEMALE);
            } else {
                myTrainer.setGender(Trainer.Gender.OTHER);
            }
            updateLabels();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Trainer Name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates text label for trainer information
    @Override
    public void updateLabels() {
        nameTextField.setText(myTrainer.getName());
        descriptionLabel.setText(newLineStringToMultilineLabel(myTrainer.toString()));
        if (myTrainer.getGender().equals(Trainer.Gender.MALE)) {
            spriteImageLabel.setIcon(new ImageIcon("./data/sprites/male trainer.png"));
            maleRadioButton.setSelected(true);
        } else if (myTrainer.getGender().equals(Trainer.Gender.FEMALE)) {
            spriteImageLabel.setIcon(new ImageIcon("./data/sprites/female trainer.png"));
            femaleRadioButton.setSelected(true);
        } else {
            spriteImageLabel.setIcon(new ImageIcon("./data/sprites/other trainer.png"));
            otherRadioButton.setSelected(true);
        }
    }
}
