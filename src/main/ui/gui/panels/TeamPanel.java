package ui.gui.panels;

import model.Pokemon;
import model.Team;

import javax.swing.*;
import java.awt.*;

// represents a JPanel for users to modify a specific team
public class TeamPanel extends ColorPanel {
    // top row
    private JPanel teamSelectionPanel;

    private JLabel teamSelectionLabel;
    private JComboBox<Team> teamSelectionBox;
    private Team currentTeam;

    // middle row
    private JPanel modifyTeamPanel;

    private JPanel namePanel;
    private JLabel nameLabel;
    private JTextField nameTextField;

    private JPanel addPokemonPanel;
    private JLabel addPokemonLabel;
    private JComboBox<Pokemon> addPokemonBox;

    private JPanel moveFrontPanel;
    private JLabel moveFrontLabel;

    private JButton confirmButton;

    // bottom row
    private JLabel currentTeamLabel;

    // EFFECTS: constructs a new team panel with given colour
    public TeamPanel(Color color) {
        super(color);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for team panel
    @Override
    protected void initialize() {
        this.setLayout(new GridLayout(3, 1));

        teamSelectionPanel = new JPanel();
        teamSelectionPanel.setLayout(new GridBagLayout());
        teamSelectionPanel.setBackground(colour);
        this.add(teamSelectionPanel);
        teamSelectionLabel = new JLabel("Select the team you would like to modify ");
        teamSelectionPanel.add(teamSelectionLabel);
        teamSelectionBox = new JComboBox<>();
        teamSelectionPanel.add(teamSelectionBox);
        teamSelectionBox.setPreferredSize(new Dimension(250, 30));

        modifyTeamPanel = new JPanel();
        this.add(modifyTeamPanel);
        modifyTeamPanel.setLayout(new GridLayout(2, 2));
        modifyTeamPanel.setBackground(colour);

        teamSelectionBox.addActionListener(e -> {
            currentTeam = (Team) teamSelectionBox.getSelectedItem();
            modifyTeamPanel.removeAll();
            setupModifyTeamPanel();
        });
    }

    // MODIFIES: this
    // CREATES: text boxes to allow user to edit current team details
    private void setupModifyTeamPanel() {
        if (currentTeam != null) {
            if (currentTeamLabel == null) {
                currentTeamLabel = new JLabel(currentTeam.oneLineSummary());
            } else {
                currentTeamLabel.setText(currentTeam.oneLineSummary());
            }
            this.add(currentTeamLabel);
            currentTeamLabel.setVisible(true);

            modifyTeamPanel.setVisible(true);
            setupNamePanel();
            setupAddPokemonPanel();
            setupMoveFrontPanel();

            confirmButton = new JButton("Confirm Changes");
            modifyTeamPanel.add(confirmButton);
            confirmButton.addActionListener(e -> {
                handleConfirm();
            });

        } else {
            modifyTeamPanel.setVisible(false);
            if (currentTeamLabel != null) {
                currentTeamLabel.setVisible(false);
            }
        }
    }

    // EFFECT: creates tools need to change team's name, Pokemon team members, and position of members
    private void setupNamePanel() {
        namePanel = new JPanel();
        modifyTeamPanel.add(namePanel);
        namePanel.setLayout(new FlowLayout());
        namePanel.setBackground(colour);
        nameLabel = new JLabel("Change Team " + currentTeam.getName() + "'s name: ");
        namePanel.add(nameLabel);
        nameTextField = new JTextField(10);
        namePanel.add(nameTextField);
        nameTextField.setText(currentTeam.getName());
    }

    // EFFECTS: creates label and combo box for users to pick pokemon to add to team
    private void setupAddPokemonPanel() {
        addPokemonPanel = new JPanel();
        modifyTeamPanel.add(addPokemonPanel);
        addPokemonPanel.setLayout(new FlowLayout());
        addPokemonPanel.setBackground(colour);
        addPokemonLabel = new JLabel("Add Pokemon");
        addPokemonPanel.add(addPokemonLabel);
        addPokemonBox = new JComboBox<>();
        addPokemonPanel.add(addPokemonBox);
        addPokemonBox.setPreferredSize(new Dimension(250, 30));
        addPokemonBox.removeAllItems();
        addPokemonBox.addItem(null);
        for (Pokemon p : myTrainer.getRanch()) {
            addPokemonBox.addItem(p);
        }
    }

    // EFFECTS: creates buttons needed for user to pick which Pokemon should be moved to front
    private void setupMoveFrontPanel() {
        moveFrontPanel = new JPanel();
        modifyTeamPanel.add(moveFrontPanel);
        moveFrontPanel.setLayout(new FlowLayout());
        moveFrontPanel.setBackground(colour);
        moveFrontLabel = new JLabel("Move Front");
        moveFrontPanel.add(moveFrontLabel);
    }

    // MODIFIES: this
    // EFFECTS: changes the information of the Pokemon based on the users inputed fields
    private void handleConfirm() {
        String userTeamName = nameTextField.getText();
        Pokemon pokemonToAdd = (Pokemon) addPokemonBox.getSelectedItem();

        if (userTeamName.isBlank()) {
            JOptionPane.showMessageDialog(null, "Invlaid Team Name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (!currentTeam.getName().equals(userTeamName) && myTrainer.getAllTeamNames().contains(userTeamName)) {
            JOptionPane.showMessageDialog(null, "Duplicate Team Name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (currentTeam.containsPokemon(pokemonToAdd)) {
            JOptionPane.showMessageDialog(null, "That pokemon is already on your team!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            currentTeam.setName(userTeamName);
            nameLabel.setText("Change Team " + currentTeam.getName() + "'s name: ");
            if (pokemonToAdd != null) {
                myTrainer.addPokemonToTeam(pokemonToAdd, currentTeam.getName());
                currentTeamLabel.setText(currentTeam.oneLineSummary());
                teamSelectionBox.requestFocus();
            }
        }
    }

    // EFFECTS: updates text label for specific team information
    @Override
    public void updateLabels() {
        teamSelectionBox.removeAllItems();
        teamSelectionBox.addItem(null);
        for (Team t : myTrainer.getTeams()) {
            teamSelectionBox.addItem(t);
        }
    }
}
