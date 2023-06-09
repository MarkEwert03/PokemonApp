package ui.gui.panels;

import model.Trainer;

import javax.swing.*;
import java.awt.*;

// represents a JPanel for users to modify their list of teams
public class ListOfTeamsPanel extends ColorPanel {
    private JLabel teamsInfoLabel;
    private JButton addTeamButton;
    private JButton deleteTeamButton;

    // EFFECTS: constructs a new list of teams panel with given colour and trainer
    public ListOfTeamsPanel(Color color, Trainer globalTrainer) {
        super(color, globalTrainer);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for list of teams panel
    @Override
    protected void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createRigidArea(new Dimension(20, 20)));

        addTeamButton = new JButton("Add Team");
        this.add(addTeamButton);
        addTeamButton.addActionListener(e -> {
            handleAddTeam();
        });
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        deleteTeamButton = new JButton("Delete Team");
        this.add(deleteTeamButton);
        deleteTeamButton.addActionListener(e -> {
            handleDeleteTeam();
        });
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        teamsInfoLabel = new JLabel();
        this.add(teamsInfoLabel);
    }

    // EFFECTS: prompts user to make a new team
    private void handleAddTeam() {
        String teamName = textPrompt("Name of team");
        while (teamName != null && myTrainer.getAllTeamNames().contains(teamName)) {
            JOptionPane.showMessageDialog(null, "Duplicate Team name",
                    "Error", JOptionPane.ERROR_MESSAGE);
            teamName = textPrompt("Name of team");
        }
        if (teamName == null) {
            return;
        }
        myTrainer.makeTeam(teamName);
        updateLabels();
    }

    // EFFECTS: prompts the user to delete a team
    private void handleDeleteTeam() {
        if (myTrainer.hasZeroTeams()) {
            JOptionPane.showMessageDialog(null, "You haven't made any teams yet!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] teamNames = myTrainer.getAllTeamNames().toArray(new String[0]);

            String name = (String) JOptionPane.showInputDialog(null,
                    "Which team do you wish to delete?",
                    "Team Selector", JOptionPane.QUESTION_MESSAGE, null, teamNames, null);
            if (name == null) {
                return;
            }
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Are you sure? This action cannot be undone...",
                    "Confirmation Window", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                myTrainer.deleteTeam(name);
            }
            updateLabels();
        }
    }

    // EFFECTS: updates text label for list of teams information
    @Override
    public void updateLabels() {
        String teamsInfoText = newLineStringToMultilineLabel(myTrainer.detailedListOfTeamsSummary());
        if (myTrainer.hasZeroTeams()) {
            teamsInfoText = "You have no teams";
        }
        teamsInfoLabel.setText(teamsInfoText);
    }
}
