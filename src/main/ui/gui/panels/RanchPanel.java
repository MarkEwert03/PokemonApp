package ui.gui.panels;

import model.Pokemon;
import model.Species;
import model.Trainer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

// represents a JPanel for users to modify their ranch of Pokemon
public class RanchPanel extends ColorPanel {
    private JLabel ranchInfoLabel;
    private JButton addPokemonButton;
    private JButton deletePokemonButton;

    // EFFECTS: constructs a new ranch panel with given colour and trainer
    public RanchPanel(Color color, Trainer globalTrainer) {
        super(color, globalTrainer);
    }

    // MODIFIES: this
    // EFFECTS: sets up JComponents needed for trainer ranch panel
    @Override
    protected void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createRigidArea(new Dimension(20, 20)));

        addPokemonButton = new JButton("Add Pokemon");
        this.add(addPokemonButton);
        addPokemonButton.addActionListener(e -> {
            handleAddPokemon();
        });
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        deletePokemonButton = new JButton("Delete Pokemon");
        this.add(deletePokemonButton);
        deletePokemonButton.addActionListener(e -> {
            handleDeletePokemon();
        });
        this.add(Box.createRigidArea(new Dimension(0, 20)));

        ranchInfoLabel = new JLabel();
        this.add(ranchInfoLabel);
    }

    // MODIFIES: this
    // EFFECTS: creates new window to let users add a new Pokemon to ranch
    private void handleAddPokemon() {
        // line below from https://stackoverflow.com/questions/13783295
        String[] species = Arrays.stream((Species.class).getEnumConstants()).map(Enum::name).toArray(String[]::new);

        String pokeSpecies = (String) JOptionPane.showInputDialog(null, "Species of Pokemon",
                "Species Selector", JOptionPane.QUESTION_MESSAGE, null, species, null);
        if (pokeSpecies == null) {
            return;
        }

        String pokeNickname = textPrompt("Nickname of Pokemon");
        while (pokeNickname != null && myTrainer.getAllPokemonNicknames().contains(pokeNickname)) {
            JOptionPane.showMessageDialog(null, "Duplicate Nickname",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            pokeNickname = textPrompt("Nickname of Pokemon");
        }
        if (pokeNickname == null) {
            return;
        }
        myTrainer.addPokemonToRanch(new Pokemon(Species.valueOf(pokeSpecies.toUpperCase()), pokeNickname,
                false, Pokemon.Gender.MALE));
        updateLabels();
    }

    // MODIFIES: this
    // EFFECTS: creates new window to let users delete a pokemon from ranch and all teams
    private void handleDeletePokemon() {
        if (myTrainer.hasZeroPokemon()) {
            JOptionPane.showMessageDialog(null, "You haven't made any Pokemon yet!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String pokeNickname = textPrompt("Nickname of Pokemon");
            while (pokeNickname != null && !myTrainer.getAllPokemonNicknames().contains(pokeNickname)) {
                JOptionPane.showMessageDialog(null, "Incorrect Nickname",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                pokeNickname = textPrompt("Nickname of Pokemon");
            }
            if (pokeNickname == null) {
                return;
            }
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Are you sure? This action cannot be undone...",
                    "Confirmation Window", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                myTrainer.deletePokemon(pokeNickname);
            }
            updateLabels();
        }
    }

    // EFFECTS: updates text label for ranch information
    @Override
    public void updateLabels() {
        String ranchInfoText = newLineStringToMultilineLabel(myTrainer.detailedRanchSummary());
        if (myTrainer.hasZeroPokemon()) {
            ranchInfoText = "Ranch is empty";
        }
        ranchInfoLabel.setText(ranchInfoText);
    }
}
