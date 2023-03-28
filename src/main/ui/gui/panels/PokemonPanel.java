package ui.gui.panels;

import model.Pokemon;

import javax.swing.*;
import java.awt.*;

// represents a JPanel for users to modify a specific Pokemon
public class PokemonPanel extends ColorPanel {
    private JPanel pokemonSelectionPanel;
    private JLabel pokemonSelectionLabel;
    private JComboBox<Pokemon> pokemonSelectionBox;
    private Pokemon currentPokemon;

    private JPanel modifyPokemonPanel;

    private JPanel nicknamePanel;
    private JLabel nicknameLabel;
    private JTextField nicknameTextField;

    private JPanel genderPanel;
    private JLabel genderLabel;

    private JPanel shinyPanel;
    private JLabel shinyLabel;

    private JButton confirmButton;

    // EFFECTS: constructs a new pokemon panel with given colour
    public PokemonPanel(Color color) {
        super(color);
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

        this.add(Box.createHorizontalBox());

        pokemonSelectionBox.addActionListener(e -> {
            currentPokemon = (Pokemon) pokemonSelectionBox.getSelectedItem();
            modifyPokemonPanel.removeAll();
            modifyPokemonMenu();
        });

        modifyPokemonPanel = new JPanel();
        this.add(modifyPokemonPanel);
        modifyPokemonPanel.setLayout(new GridLayout(2, 2));
        modifyPokemonPanel.setBackground(colour);
    }

    // MODIFIES: this
    // CREATES: text boxes to allow user to edit current Pokemon details
    private void modifyPokemonMenu() {
        if (currentPokemon != null) {
            modifyPokemonPanel.setVisible(true);
            createModifyingTools();

            confirmButton = new JButton("Confirm Changes");
            modifyPokemonPanel.add(confirmButton);

            confirmButton.addActionListener(e -> {
                handleConfirm();
            });
        } else {
            modifyPokemonPanel.setVisible(false);
        }
    }

    // EFFECT: creates tools need to change pokemon's nickname, gender, and shiny status
    private void createModifyingTools() {
        nicknamePanel = new JPanel();
        modifyPokemonPanel.add(nicknamePanel);
        nicknamePanel.setLayout(new FlowLayout());
        nicknamePanel.setBackground(colour);
        nicknameLabel = new JLabel("Change " + currentPokemon.getNickname() + "'s nickname: ");
        nicknamePanel.add(nicknameLabel);
        nicknameTextField = new JTextField(10);
        nicknamePanel.add(nicknameTextField);

        genderPanel = new JPanel();
        modifyPokemonPanel.add(genderPanel);
        genderPanel.setLayout(new FlowLayout());
        genderPanel.setBackground(colour);
        genderLabel = new JLabel("Change " + currentPokemon.getNickname() + "'s gender: ");
        genderPanel.add(genderLabel);

        shinyPanel = new JPanel();
        modifyPokemonPanel.add(shinyPanel);
        shinyPanel.setLayout(new FlowLayout());
        shinyPanel.setBackground(colour);
        shinyLabel = new JLabel("Change " + currentPokemon.getNickname() + "'s shiny status: ");
        shinyPanel.add(shinyLabel);
    }

    // MODIFIES: this
    // EFFECTS: changes the information of the Pokemon based on the users inputed fields
    private void handleConfirm() {
        String userNickname = nicknameTextField.getText();
        if (!userNickname.isBlank() && !myTrainer.getAllPokemonNicknames().contains(userNickname)) {
            currentPokemon.setNickname(userNickname);
            nicknameLabel.setText("Change " + currentPokemon.getNickname() + "'s nickname: ");
            genderLabel.setText("Change " + currentPokemon.getNickname() + "'s gender: ");
            shinyLabel.setText("Change " + currentPokemon.getNickname() + "'s shiny status: ");
            pokemonSelectionBox.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Duplicate Nickname",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: updates text label for specific pokemon information
    @Override
    public void updateLabels() {
        pokemonSelectionBox.removeAllItems();
        pokemonSelectionBox.addItem(null);
        for (Pokemon p : myTrainer.getRanch()) {
            pokemonSelectionBox.addItem(p);
        }
    }
}
