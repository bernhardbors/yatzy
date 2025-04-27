package no.uib.inf101.View.setupview;

import javax.swing.*;

import no.uib.inf101.View.YatzyView;
import no.uib.inf101.View.components.YatzyStyle;

import java.awt.*;
import java.util.function.Consumer;

/**
 * The start menu panel for choosing Yatzy variant, viewing highscores or
 * exiting the game.
 */
public class StartScreenPanel extends JPanel {
    private final JButton yatzyButton;
    private final JButton maxiYatzyButton;
    private final JButton highScoreButton;
    private final JButton quitButton;

    /**
     * Constructs the start screen panel.
     *
     * @param onGameSelected callback with true for MaxiYatzy, false for normal
     */
    public StartScreenPanel(Consumer<Boolean> onGameSelected) {
        setLayout(new GridBagLayout());
        setBackground(YatzyStyle.COLOR_BG);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel logoLabel = new JLabel(YatzyView.getYatzyIcon());
        add(logoLabel, gbc);

        gbc.gridy++;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 5));
        buttonPanel.setBackground(YatzyStyle.COLOR_BG);

        yatzyButton = new JButton("Start Yatzy");
        maxiYatzyButton = new JButton("Start Maxi Yatzy");
        highScoreButton = new JButton("Highscore");
        quitButton = new JButton("Avslutt");

        JButton[] buttons = { yatzyButton, maxiYatzyButton, highScoreButton, quitButton };
        for (JButton button : buttons) {
            button.setFont(YatzyStyle.FONT_BUTTON);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            buttonPanel.add(button);
        }

        yatzyButton.addActionListener(e -> onGameSelected.accept(false));
        maxiYatzyButton.addActionListener(e -> onGameSelected.accept(true));
        highScoreButton.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new HighscorePanel(topFrame));
            topFrame.revalidate();
            topFrame.repaint();
        });
        quitButton.addActionListener(e -> System.exit(0));

        add(buttonPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(YatzyStyle.COLOR_BG);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
