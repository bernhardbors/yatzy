package no.uib.inf101.View;

import javax.swing.*;

import no.uib.inf101.Model.YatzyModel;
import no.uib.inf101.Model.score.IScoreCategory;
import no.uib.inf101.Model.score.ScoreCategory;
import no.uib.inf101.View.components.ControlView;
import no.uib.inf101.View.components.DiceView;
import no.uib.inf101.View.components.ScoreView;
import no.uib.inf101.View.setupview.GameOverPanel;
import no.uib.inf101.grid.CellPosition;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * The main view panel for the Yatzy game.
 * Combines DiceView, ControlView, and ScoreView into a single panel.
 */
public class YatzyView<T extends Enum<T> & IScoreCategory> extends JPanel {
    private final YatzyModel model;
    private final DiceView diceView = new DiceView();
    private final ControlView controlView = new ControlView();
    private final ScoreView scoreView = new ScoreView();
    private final T[] categories;

    /**
     * Constructs the YatzyView with the given model.
     *
     * @param model the Yatzy model
     */
    public YatzyView(YatzyModel model, T[] categories) {
        this.model = model;
        this.categories = categories;

        setLayout(new BorderLayout());
        add(scoreView, BorderLayout.WEST);
        add(createRightSidePanel(), BorderLayout.CENTER);

        updateView();
    }

    private JPanel createRightSidePanel() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        JLabel logoLabel = new JLabel(getYatzyIcon(), SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.BLUE);
        logoPanel.add(logoLabel);

        rightPanel.add(logoPanel, BorderLayout.NORTH);
        rightPanel.add(diceView, BorderLayout.CENTER);
        rightPanel.add(controlView, BorderLayout.SOUTH);
        return rightPanel;
    }

    /**
     * Updates all subviews with current model state.
     * Also shows Game Over panel if game is finished.
     */
    public void updateView() {
        updateDiceView();
        updateControlView();
        updateScoreView();

        if (model.isGameFinished()) {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (topFrame != null) {
                topFrame.setContentPane(new GameOverPanel(topFrame, model.getPlayers(), isMaxiGame()));
                topFrame.revalidate();
                topFrame.repaint();
            }
        }
    }

    private void updateDiceView() {
        diceView.setDice(model.getDice());
    }

    private void updateControlView() {
        controlView.update(
                model.getCurrentPlayer().getName(),
                model.getRollsThisTurn(),
                model.getRollsThisTurn() < 3 && !model.isGameFinished());
    }

    private void updateScoreView() {
        scoreView.updateScoreGrid(model.getPlayers(), categories);
    }

    /**
     * Returns the Yatzy logo icon, scaled for display.
     *
     * @return an ImageIcon of the Yatzy logo
     */
    public static ImageIcon getYatzyIcon() {
        String path = "/images/yatzylogo.png";
        java.net.URL location = YatzyView.class.getResource(path);
        Image img = new ImageIcon(location).getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    /**
     * Returns the score category from a cell position.
     *
     * @param pos the cell position
     * @return the score category
     */
    public IScoreCategory getCategoryFromCellPosition(CellPosition pos) {
        return scoreView.getScoreCategoryFromRow(pos.row());
    }

    /**
     * Getter for the categories.
     * 
     * @return the array of categories
     */
    public T[] getCategories() {
        return categories;
    }

    /**
     * Sets the listener for the roll button click.
     *
     * @param listener the listener to set
     */
    public void setOnRollClicked(Runnable listener) {
        controlView.setOnRoll(listener);
    }

    /**
     * Sets the listener for the dice click.
     *
     * @param listener the listener to set
     */
    public void setOnDiceClicked(IntConsumer listener) {
        diceView.setOnDieClicked((Integer i) -> listener.accept(i));
    }

    /**
     * Sets the listener for the score cell click.
     *
     * @param listener the listener to set
     */
    public void setOnScoreCellClicked(Consumer<CellPosition> listener) {
        scoreView.setOnCellClicked(listener);
    }

    private boolean isMaxiGame() {
        return categories.length > ScoreCategory.values().length;
    }
}
