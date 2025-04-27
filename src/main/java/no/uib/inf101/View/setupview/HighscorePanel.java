package no.uib.inf101.View.setupview;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import no.uib.inf101.GameLauncher;
import no.uib.inf101.Model.score.HighscoreManager;
import no.uib.inf101.View.components.YatzyStyle;

/**
 * A panel that displays the high scores for Yatzy and MaxiYatzy.
 * It shows the top 10 scores in a text area and provides a button to return to
 * the start screen.
 */
public class HighscorePanel extends JPanel {

    /**
     * Constructs a HighscorePanel.
     *
     * @param frame the main application frame
     */
    public HighscorePanel(JFrame frame) {
        setLayout(new GridBagLayout());
        setBackground(YatzyStyle.COLOR_BG);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel("Highscores");
        title.setFont(YatzyStyle.FONT_LARGE_BOLD);
        title.setForeground(YatzyStyle.COLOR_TEXT);
        add(title, gbc);

        gbc.gridy++;
        JPanel scoresPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        scoresPanel.setBackground(YatzyStyle.COLOR_BG);
        scoresPanel.setPreferredSize(new Dimension(500, 250));

        JTextArea yatzyArea = createHighscoreArea(HighscoreManager.readHighscores(false), "Yatzy Highscores");
        JTextArea maxiArea = createHighscoreArea(HighscoreManager.readHighscores(true), "MaxiYatzy Highscores");

        scoresPanel.add(new JScrollPane(yatzyArea));
        scoresPanel.add(new JScrollPane(maxiArea));

        add(scoresPanel, gbc);

        gbc.gridy++;
        JButton backButton = new JButton("Tilbake");
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

    private JTextArea createHighscoreArea(List<HighscoreManager.ScoreEntry> scores, String title) {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(YatzyStyle.FONT_SCORE_PLAIN);
        area.setForeground(YatzyStyle.COLOR_TEXT);
        area.setBackground(YatzyStyle.COLOR_BG);

        StringBuilder sb = new StringBuilder(title + "\n\n");
        int rank = 1;
        for (HighscoreManager.ScoreEntry entry : scores) {
            sb.append(rank++).append(". ").append(entry.name()).append(" - ").append(entry.score()).append("\n");
        }
        area.setText(sb.toString());
        return area;
    }
}
