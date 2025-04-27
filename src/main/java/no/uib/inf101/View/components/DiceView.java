package no.uib.inf101.View.components;

import no.uib.inf101.Model.dices.IDie;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * A view that displays the dice in the Yatzy game.
 * The dice can be held or released by clicking on them.
 */
public class DiceView extends JPanel {
    private static final int DIE_SIZE = 128;
    private static final int HELD_DIE_SIZE = 96;

    private JButton[] diceButtons = new JButton[0];
    private IDie[] dice = new IDie[0];
    private Consumer<Integer> onDieClicked;

    /**
     * Constructor for the DiceView.
     */
    public DiceView() {
        setBackground(YatzyStyle.COLOR_BG);
    }

    /**
     * Updates the displayed dice based on the given dice array.
     *
     * @param dice the current state of all dice
     */
    public void setDice(IDie[] dice) {
        if (this.dice.length != dice.length) {
            this.dice = dice;
            rebuildDiceButtons();
        } else {
            this.dice = dice;
        }
        updateDice();
    }

    private void rebuildDiceButtons() {
        removeAll();
        diceButtons = new JButton[dice.length];

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        for (int i = 0; i < dice.length; i++) {
            final int index = i;
            JButton button = createDieButton(index);

            gbc.gridx = (dice.length == 5 && i == 4) ? 0 : i % 2;
            gbc.gridy = (dice.length == 5 && i == 4) ? 2 : i / 2;
            gbc.gridwidth = (dice.length == 5 && i == 4) ? 2 : 1;
            gbc.anchor = GridBagConstraints.CENTER;

            diceButtons[i] = button;
            add(button, gbc);
        }
        revalidate();
    }

    /**
     * Sets the listener that gets called when a die is clicked.
     *
     * @param listener callback with the index of the clicked die
     */
    public void setOnDieClicked(Consumer<Integer> listener) {
        this.onDieClicked = listener;
    }

    /**
     * Updates the displayed dice based on the current state of the model.
     */
    public void updateDice() {
        if (dice == null || diceButtons.length != dice.length)
            return;

        for (int i = 0; i < dice.length; i++) {
            boolean held = dice[i].isHeld();
            int size = held ? HELD_DIE_SIZE : DIE_SIZE;
            JButton button = diceButtons[i];
            button.setPreferredSize(new Dimension(size, size));
            button.setIcon(getDieIcon(dice[i].getValue(), held));
        }
    }

    private JButton createDieButton(int index) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(DIE_SIZE, DIE_SIZE));
        button.addActionListener(e -> {
            if (onDieClicked != null) {
                onDieClicked.accept(index);
            }
        });
        return button;
    }

    /**
     * Retrieves an icon representing a die´s current value and held state.
     * 
     * @param value value the die value (1–6)
     * @param held  true if the die is held
     * @return the appropriate die icon
     */
    private ImageIcon getDieIcon(int value, boolean held) {
        int iconSize = held ? HELD_DIE_SIZE : DIE_SIZE;
        String path = "/images/die" + value + ".png";
        java.net.URL location = getClass().getResource(path);
        Image img = new ImageIcon(location)
                .getImage()
                .getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
