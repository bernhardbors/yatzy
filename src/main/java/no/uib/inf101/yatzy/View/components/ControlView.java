package no.uib.inf101.yatzy.View.components;

import javax.swing.*;

import java.awt.*;

/**
 * A panel showing the current player, roll button and roll counter.
 */
public class ControlView extends JPanel {
    private final JLabel currentPlayerLabel = new JLabel();
    private final JLabel rollCounterLabel = new JLabel("Kast: 0/3");
    private final JButton rollButton = new JButton("KAST");
    private final JButton quitButton = new JButton("Avslutt");
    private Runnable onRoll;

    /**
     * Constructs the control panel and binds the roll action.
     */
    public ControlView() {
        setLayout(new FlowLayout());
        setBackground(YatzyStyle.COLOR_BG);

        currentPlayerLabel.setFont(YatzyStyle.FONT_LARGE_BOLD);
        currentPlayerLabel.setForeground(YatzyStyle.COLOR_TEXT);
        currentPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        rollCounterLabel.setFont(YatzyStyle.FONT_LARGE_BOLD);
        rollCounterLabel.setForeground(YatzyStyle.COLOR_TEXT);
        rollCounterLabel.setHorizontalAlignment(SwingConstants.CENTER);

        rollButton.setFont(YatzyStyle.FONT_BUTTON);
        rollButton.setForeground(YatzyStyle.COLOR_BUTTON_TEXT);
        rollButton.addActionListener(e -> {
            if (onRoll != null) {
                onRoll.run();
            }
        });

        quitButton.setFont(YatzyStyle.FONT_BUTTON);
        quitButton.setForeground(YatzyStyle.COLOR_BUTTON_TEXT);
        quitButton.addActionListener(e -> System.exit(0));

        add(currentPlayerLabel);
        add(rollButton);
        add(rollCounterLabel);
        add(quitButton);

    }

    /**
     * Updates display of current player and roll counter.
     * 
     * @param playerName the current player's name
     * @param rolls      the number of rolls this turn
     * @param canRoll    whether rolling is still allowed
     */
    public void update(String playerName, int rolls, boolean canRoll) {
        currentPlayerLabel.setText("Spiller: " + playerName);
        rollCounterLabel.setText("Kast: " + rolls + "/3");
        rollButton.setEnabled(canRoll);
    }

    /**
     * Sets a callback for when the roll button is pressed.
     *
     * @param onRoll the action to perform when rolling
     */
    public void setOnRoll(Runnable onRoll) {
        this.onRoll = onRoll;
    }
}