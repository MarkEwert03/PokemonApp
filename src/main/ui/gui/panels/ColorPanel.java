package ui.gui.panels;

import model.Trainer;
import ui.gui.PokemonAppGUI;

import javax.swing.*;
import java.awt.*;

// represents an abstract JPanel with a color for itself and the tab for it as well as trainer for subclasses to use
public abstract class ColorPanel extends JPanel {
    private Color colour;
    protected Trainer myTrainer;

    // EFFECTS: constructs a new color panel with given color
    public ColorPanel(Color colour) {
        super();
        this.colour = colour;
        myTrainer = new Trainer("Trainer");
        initialize();
    }

    // MODIFIES: this
    // EFFECTS: abstract method to set up specifics for each colour panel
    protected abstract void initialize();

    // EFFECTS: returns the color of this panel
    public Color getColour() {
        return colour;
    }
}
