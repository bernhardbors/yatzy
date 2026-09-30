package no.uib.inf101.yatzy;

import java.util.List;

import javax.swing.JFrame;

import no.uib.inf101.yatzy.Controller.YatzyController;
import no.uib.inf101.yatzy.Model.YatzyModel;
import no.uib.inf101.yatzy.Model.score.ScoreCalculator;
import no.uib.inf101.yatzy.Model.score.ScoreCategory;
import no.uib.inf101.yatzy.View.YatzyView;
import no.uib.inf101.yatzy.View.setupview.PlayerSetup;
import no.uib.inf101.yatzy.maxiyatzy.model.MaxiYatzyModel;
import no.uib.inf101.yatzy.maxiyatzy.model.score.MaxiScoreCalculator;
import no.uib.inf101.yatzy.maxiyatzy.model.score.MaxiScoreCategory;
import no.uib.inf101.yatzy.maxiyatzy.view.MaxiYatzyView;

/**
 * A utility class for launching either a Yatzy or MaxiYatzy game.
 * Sets up model, view, and controller for the selected variant.
 */
public class GameLauncher {
    public static final String WINDOW_TITLE = "Yatzy!";

    /**
     * Starts the selected Yatzy variant after asking for player names.
     * 
     * @param maxiYatzy true to start MaxiYatzy, false for normal Yatzy
     */
    public static void startGame(boolean maxiYatzy) {
        List<String> playerNames = PlayerSetup.getPlayerNames();
        if (playerNames == null || playerNames.isEmpty())
            return;

        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (maxiYatzy) {
            launchMaxiYatzy(frame, playerNames);
        } else {
            launchYatzy(frame, playerNames);
        }

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void launchYatzy(JFrame frame, List<String> playerNames) {
        YatzyModel model = new YatzyModel(playerNames);
        YatzyView view = new YatzyView<>(model, ScoreCategory.values());
        ScoreCalculator calculator = new ScoreCalculator();
        new YatzyController(model, view,
                (dice, cat) -> calculator.calculateScore(dice, (ScoreCategory) cat));
        frame.setContentPane(view);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static void launchMaxiYatzy(JFrame frame, List<String> playerNames) {
        MaxiYatzyModel model = new MaxiYatzyModel(playerNames);
        MaxiYatzyView view = new MaxiYatzyView(model);
        MaxiScoreCalculator calculator = new MaxiScoreCalculator();
        new YatzyController(model, view,
                (dice, cat) -> calculator.calculateScore(dice, (MaxiScoreCategory) cat));
        frame.setContentPane(view);
    }
}
