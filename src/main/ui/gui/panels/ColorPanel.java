package ui.gui.panels;

import model.Trainer;
import ui.gui.PokemonAppGUI;

import javax.swing.*;
import java.awt.*;

// represents an abstract JPanel with a color for itself and the tab for it as well as trainer for subclasses to use
public abstract class ColorPanel extends JPanel {
    protected Color colour;
    protected Trainer myTrainer;

    // EFFECTS: constructs a new color panel with given color
    public ColorPanel(Color colour, Trainer globalTrainer) {
        super();
        this.colour = colour;
        myTrainer = globalTrainer;
        initialize();
    }

    // EFFECTS: returns the color of this panel
    public Color getColour() {
        return colour;
    }

    // EFFECTS: sets myTrainer to updated Trainer
    public void setMyTrainer(Trainer updatedTrainer) {
        myTrainer = updatedTrainer;
    }

    // EFFECTS: gets updated trainer (used after load)
    public Trainer getMyTrainer() {
        return myTrainer;
    }

    // MODIFIES: this
    // EFFECTS: abstract method to set up specifics for each colour panel
    protected abstract void initialize();

    // EFFECTS: abstract method that will update whatever labels are on screen to show current trainer info
    public abstract void updateLabels();

    // EFFECTS: helper that converts string into HTML format for multiline JLabels
    protected String newLineStringToMultilineLabel(String str) {
        String replaced = str.replace("\n", "<br>");
        return "<html>" + replaced + "</html>";
    }

    // EFFECTS: helper for opening a new text window for users to input information
    protected String textPrompt(String message) {
        return JOptionPane.showInputDialog(this, message, null);
    }
}
