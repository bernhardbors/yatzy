package no.uib.inf101.Model.score;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Manages the high scores for Yatzy and Maxi Yatzy games.
 * It reads and writes high scores to files, and maintains a list of the top 10
 * scores.
 */
public class HighscoreManager {
    private static final String YATZY_FILE = "src/main/resources/highscores_yatzy.txt";
    private static final String MAXI_FILE = "src/main/resources/highscores_maxiyatzy.txt";
    private static final int MAX_ENTRIES = 10;

    /**
     * Adds a new high score for the specified game type (Yatzy or Maxi Yatzy).
     *
     * @param playerName the name of the player
     * @param score      the score to add
     * @param isMaxi     true if the score is for Maxi Yatzy, false for Yatzy
     */
    public static void addHighscore(String playerName, int score, boolean isMaxi) {
        String file = isMaxi ? MAXI_FILE : YATZY_FILE;
        List<ScoreEntry> scores = readHighscores(isMaxi);
        scores.add(new ScoreEntry(playerName, score));
        scores.sort(Comparator.comparingInt(ScoreEntry::score).reversed());

        if (scores.size() > MAX_ENTRIES) {
            scores = scores.subList(0, MAX_ENTRIES);
        }

        writeHighscores(file, scores);
    }

    /**
     * Reads the high scores from the specified file.
     *
     * @param isMaxi true if reading Maxi Yatzy scores, false for Yatzy
     * @return a list of high score entries
     */
    public static List<ScoreEntry> readHighscores(boolean isMaxi) {
        String file = isMaxi ? MAXI_FILE : YATZY_FILE;
        List<ScoreEntry> scores = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    scores.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
                }
            }
        } catch (IOException e) {
            // file may not exist yet
        }
        return scores;
    }

    private static void writeHighscores(String file, List<ScoreEntry> scores) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {
            for (ScoreEntry entry : scores) {
                pw.println(entry.name() + "," + entry.score());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Represents a high score entry with a player's name and score.
     *
     * @param name  the name of the player
     * @param score the score of the player
     */
    public record ScoreEntry(String name, int score) {
    }
}