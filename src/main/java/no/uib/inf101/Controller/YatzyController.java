package no.uib.inf101.Controller;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import no.uib.inf101.Model.YatzyModel;
import no.uib.inf101.Model.dices.DiceUtils;
import no.uib.inf101.Model.player.IPlayer;
import no.uib.inf101.Model.score.HighscoreManager;
import no.uib.inf101.Model.score.IScoreCategory;
import no.uib.inf101.Model.score.ScoreCategory;
import no.uib.inf101.View.YatzyView;
import no.uib.inf101.grid.CellPosition;

/**
 * Controller class for handling user interaction and updating
 * the Yatzy game model and view accordingly.
 *
 * @param <T> a type that is both an enum and an IScoreCategory
 */
public class YatzyController<T extends Enum<T> & IScoreCategory> {
    private final YatzyModel model;
    private final YatzyView<T> view;
    private final ScoreCalculatorFunction calculator;

    /**
     * Constructs a YatzyController.
     * 
     * @param model      the game model to manipulate
     * @param view       the game view to update
     * @param calculator a function for calculating scores
     */
    public YatzyController(YatzyModel model, YatzyView<T> view, ScoreCalculatorFunction calculator) {
        this.model = model;
        this.view = view;
        this.calculator = calculator;

        this.view.setOnRollClicked(this::handleRollClick);
        this.view.setOnDiceClicked(this::handleDieClick);
        this.view.setOnScoreCellClicked(this::handleScoreClick);

        this.view.updateView();
    }

    private void handleRollClick() {
        startRollAnimation();
    }

    private void startRollAnimation() {
        Timer rollTimer = new Timer(250, null);
        rollTimer.addActionListener(new ActionListener() {
            int ticks = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                model.simulateRoll();
                view.updateView();
                ticks++;

                if (ticks >= 4) {
                    rollTimer.stop();
                    model.rollDice();
                    view.updateView();
                }
            }
        });

        rollTimer.start();
    }

    private void handleDieClick(int index) {
        if (model.getRollsThisTurn() == 0) {
            return;
        }
        model.getDice()[index].toggleHeld();
        view.updateView();
    }

    private void handleScoreClick(CellPosition pos) {
        if (pos.col() != 0)
            return;
        if (model.getRollsThisTurn() == 0)
            return;
        IScoreCategory category = view.getCategoryFromCellPosition(pos);
        tryScore(category);
    }

    private void tryScore(IScoreCategory category) {
        if (category == null)
            return;
        var player = model.getCurrentPlayer();
        var card = player.getScoreCard();

        if (!card.isCategoryUsed(category)) {
            int[] dice = DiceUtils.getValues(model.getDice());
            int score = calculator.calculate(dice, category);
            card.setScore(category, score);

            if (model.isGameFinished()) {
                IPlayer winner = model.getWinner();
                boolean isMaxi = viewIsMaxi();
                HighscoreManager.addHighscore(winner.getName(), winner.getScoreCard().getTotalScore(), isMaxi);
            }

            model.newTurn();
            view.updateView();
        }
    }

    private boolean viewIsMaxi() {
        return view.getCategories().length > ScoreCategory.values().length;
    }
}
