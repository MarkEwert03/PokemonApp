package ui.gui.panels;

import model.Pokemon;
import model.Trainer;

import javax.swing.*;
import java.awt.*;

// represents a JPanel for users to modify a specific Pokemon
public class PokemonPanel extends ColorPanel {
    //top row
    private JPanel pokemonSelectionPanel;

    private JLabel pokemonSelectionLabel;
    private JComboBox<Pokemon> pokemonSelectionBox;
    private Pokemon currentPokemon;

    // middle row
    private JPanel modifyPokemonPanel;

    private JPanel nicknamePanel;
    private JLabel nicknameLabel;
    private JTextField nicknameTextField;

    private JPanel genderPanel;
    private JLabel genderLabel;
    private ButtonGroup genderButtonGroup;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton unknownRadioButton;

    private JPanel shinyPanel;
    private JLabel shinyLabel;
    private ButtonGroup shinyButtonGroup;
    private JRadioButton yesShinyButton;
    private JRadioButton noShinyButton;

    private JButton confirmButton;

    // bottom row
    private JLabel currentPokemonLabel;

    // EFFECTS: constructs a new pokemon panel with given colour and trainer
    public PokemonPanel(Color color, Trainer globalTrainer) {
        super(color, globalTrainer);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for pokemon panel
    @Override
    protected void initialize() {
        this.setLayout(new GridLayout(3, 1));

        pokemonSelectionPanel = new JPanel();
        pokemonSelectionPanel.setLayout(new GridBagLayout());
        pokemonSelectionPanel.setBackground(colour);
        this.add(pokemonSelectionPanel);
        pokemonSelectionLabel = new JLabel("Select the Pokemon you would like to modify ");
        pokemonSelectionPanel.add(pokemonSelectionLabel);
        pokemonSelectionBox = new JComboBox<>();
        pokemonSelectionPanel.add(pokemonSelectionBox);
        pokemonSelectionBox.setPreferredSize(new Dimension(250, 30));

        modifyPokemonPanel = new JPanel();
        this.add(modifyPokemonPanel);
        modifyPokemonPanel.setLayout(new GridLayout(2, 2));
        modifyPokemonPanel.setBackground(colour);

        pokemonSelectionBox.addActionListener(e -> {
            currentPokemon = (Pokemon) pokemonSelectionBox.getSelectedItem();
            modifyPokemonPanel.removeAll();
            setupModifyPokemonPanel();
        });
    }

    // MODIFIES: this
    // CREATES: text boxes to allow user to edit current Pokemon details
    private void setupModifyPokemonPanel() {
        if (currentPokemon != null) {
            modifyPokemonPanel.setVisible(true);

            if (currentPokemonLabel == null) {
                currentPokemonLabel = new JLabel(currentPokemon.oneLineSummary(1, 1));
            } else {
                currentPokemonLabel.setText(currentPokemon.oneLineSummary(1, 1));
            }
            this.add(currentPokemonLabel);
            currentPokemonLabel.setVisible(true);

            setupNicknamePanel();
            setupGenderPanel();
            setupShinyPanel();

            confirmButton = new JButton("Confirm Changes");
            modifyPokemonPanel.add(confirmButton);

            confirmButton.addActionListener(e -> {
                handleConfirm();
            });
        } else {
            modifyPokemonPanel.setVisible(false);
            if (currentPokemonLabel != null) {
                currentPokemonLabel.setVisible(false);
            }
        }
    }

    // EFFECT: creates tools need to change Pokemon's nickname, gender, and shiny status
    private void setupNicknamePanel() {
        nicknamePanel = new JPanel();
        modifyPokemonPanel.add(nicknamePanel);
        nicknamePanel.setLayout(new FlowLayout());
        nicknamePanel.setBackground(colour);
        nicknameLabel = new JLabel("Change " + currentPokemon.getNickname() + "'s nickname ");
        nicknamePanel.add(nicknameLabel);
        nicknameTextField = new JTextField(10);
        nicknamePanel.add(nicknameTextField);
        nicknameTextField.setText(currentPokemon.getNickname());
    }

    // EFFECTS: creates 3 radio buttons for users to pick Pokemon's gender
    private void setupGenderPanel() {
        genderPanel = new JPanel();
        modifyPokemonPanel.add(genderPanel);
        genderPanel.setBackground(colour);
        genderLabel = new JLabel(currentPokemon.getNickname() + "'s gender ");
        genderPanel.add(genderLabel);

        genderButtonGroup = new ButtonGroup();
        maleRadioButton = new JRadioButton("Male");
        genderButtonGroup.add(maleRadioButton);
        genderPanel.add(maleRadioButton);
        maleRadioButton.setSelected(true);

        femaleRadioButton = new JRadioButton("Female");
        genderButtonGroup.add(femaleRadioButton);
        genderPanel.add(femaleRadioButton);

        unknownRadioButton = new JRadioButton("Unknown");
        genderPanel.add(unknownRadioButton);
        genderButtonGroup.add(unknownRadioButton);
    }

    // EFFECTS: creates 2 radio buttons for users to pick Pokemon's shiny status
    private void setupShinyPanel() {
        shinyPanel = new JPanel();
        modifyPokemonPanel.add(shinyPanel);
        shinyPanel.setBackground(colour);
        shinyLabel = new JLabel(currentPokemon.getNickname() + "'s shiny status ");
        shinyPanel.add(shinyLabel);

        shinyButtonGroup = new ButtonGroup();
        yesShinyButton = new JRadioButton("Shiny");
        shinyButtonGroup.add(yesShinyButton);
        shinyPanel.add(yesShinyButton);

        noShinyButton = new JRadioButton("Not Shiny");
        shinyButtonGroup.add(noShinyButton);
        shinyPanel.add(noShinyButton);
        noShinyButton.setSelected(true);
    }

    // MODIFIES: this
    // EFFECTS: changes the information of the Pokemon based on the users inputed fields
    private void handleConfirm() {
        if (nicknameTextField.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Invalid Nickname",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!currentPokemon.getNickname().equals(nicknameTextField.getText())
                && myTrainer.getAllPokemonNicknames().contains(nicknameTextField.getText())) {
            JOptionPane.showMessageDialog(null, "Duplicate Nickname",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {

            currentPokemon.setNickname(nicknameTextField.getText());
            nicknameLabel.setText(currentPokemon.getNickname() + "'s nickname ");
            genderLabel.setText(currentPokemon.getNickname() + "'s gender ");
            shinyLabel.setText(currentPokemon.getNickname() + "'s shiny status ");

            if (maleRadioButton.isSelected()) {
                currentPokemon.setGender(Pokemon.Gender.MALE);
            } else if (femaleRadioButton.isSelected()) {
                currentPokemon.setGender(Pokemon.Gender.FEMALE);
            } else {
                currentPokemon.setGender(Pokemon.Gender.UNKNOWN);
            }

            currentPokemon.setShiny(yesShinyButton.isSelected());
            currentPokemonLabel.setText(currentPokemon.oneLineSummary(1, 1));
            pokemonSelectionBox.requestFocus();
        }
    }

    // EFFECTS: updates text label for specific Pokemon information
    @Override
    public void updateLabels() {
        pokemonSelectionBox.removeAllItems();
        pokemonSelectionBox.addItem(null);
        for (Pokemon p : myTrainer.getRanch()) {
            pokemonSelectionBox.addItem(p);
        }
    }
}
