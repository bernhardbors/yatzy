package no.uib.inf101.yatzy.View.setupview;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import no.uib.inf101.yatzy.GameLauncher;
import no.uib.inf101.yatzy.Model.player.IPlayer;
import no.uib.inf101.yatzy.View.components.YatzyStyle;

/**
 * A panel that displays the game over screen with the final scores of all
 * players.
 * It allows the user to return to the start screen.
 */
public class GameOverPanel extends JPanel {

    /**
     * Constructs a GameOverPanel with the given frame, players, and game type.
     *
     * @param frame   the main application frame
     * @param players the list of players in the game
     * @param isMaxi  whether the game is MaxiYatzy or not
     */
    public GameOverPanel(JFrame frame, List<IPlayer> players, boolean isMaxi) {
        setLayout(new GridBagLayout());
        setBackground(YatzyStyle.COLOR_BG);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Spillet er ferdig!");
        titleLabel.setFont(YatzyStyle.FONT_LARGE_BOLD);
        titleLabel.setForeground(YatzyStyle.COLOR_TEXT);
        add(titleLabel, gbc);

        gbc.gridy++;
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(YatzyStyle.FONT_LARGE_PLAIN);
        resultArea.setForeground(YatzyStyle.COLOR_TEXT);
        resultArea.setBackground(YatzyStyle.COLOR_BG);
        resultArea.setPreferredSize(new Dimension(400, 200));

        players.sort(Comparator.comparingInt(p -> -p.getScoreCard().getTotalScore()));

        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for (IPlayer player : players) {
            sb.append(rank++).append(". ")
                    .append(player.getName())
                    .append(" - ")
                    .append(player.getScoreCard().getTotalScore())
                    .append(" poeng\n");
        }
        resultArea.setText(sb.toString());

        add(new JScrollPane(resultArea), gbc);

        gbc.gridy++;
        JButton backButton = new JButton("Tilbake til startskjerm");
        backButton.setFont(YatzyStyle.FONT_BUTTON);
        backButton.addActionListener(e -> {
            frame.setContentPane(new StartScreenPanel(isMaxiYatzy -> {
                frame.dispose();
                GameLauncher.startGame(isMaxiYatzy);
            }));
            frame.revalidate();
            frame.repaint();
        });
        add(backButton, gbc);
    }
}
