package no.uib.inf101.yatzy.View.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.List;

import no.uib.inf101.yatzy.Model.player.IPlayer;
import no.uib.inf101.yatzy.Model.score.IScoreCategory;
import no.uib.inf101.yatzy.View.CellPositionToPixelConverter;
import no.uib.inf101.yatzy.View.Inf101Graphics;
import no.uib.inf101.yatzy.grid.*;

/**
 * A panel that displays the score grid as a custom drawn component.
 * Allows selecting a category to score if it hasn't been used.
 */
public class ScoreView extends JPanel {
    private static final int NUM_EXTRA_ROWS = 4;
    private static final int MARGIN = 0;
    private static final int OUTERMARGIN = 20;
    private static final int MIN_COL_WIDTH = 130;

    private List<IPlayer> players;
    private IScoreCategory[] categories;
    private Map<CellPosition, String> textMap = new HashMap<>();
    private Map<Integer, IScoreCategory> rowToCategoryMap = new HashMap<>();
    private CellPositionToPixelConverter converter;
    private Consumer<CellPosition> onCellClicked;

    /**
     * Constructs a score view linked to the game model.
     */
    public ScoreView() {
        int width = MIN_COL_WIDTH * 5;
        this.setPreferredSize(new Dimension(width, 600));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CellPosition pos = getCellFromMouse(e.getX(), e.getY());
                if (onCellClicked != null && pos != null) {
                    onCellClicked.accept(pos);
                }
            }
        });
    }

    /**
     * Updates the score grid with the given players and categories.
     *
     * @param players    list of players to display
     * @param categories array of score categories
     */
    public void updateScoreGrid(List<IPlayer> players, IScoreCategory[] categories) {
        this.players = players;
        this.categories = categories;

        textMap.clear();
        rowToCategoryMap.clear();

        int row = 0;
        addHeaderRow(row++);
        row = addCategoryRows(row, true);
        row = addUpperSumRow(row);
        row = addBonusRow(row);
        row = addCategoryRows(row, false);
        addTotalRow(row);

        repaint();
    }

    /**
     * Sets a listener for cell clicks.
     *
     * @param listener the listener to set
     */
    public void setOnCellClicked(Consumer<CellPosition> listener) {
        this.onCellClicked = listener;
    }

    /**
     * Returns the score category corresponding to the clicked cell.
     *
     * @param pos the cell position
     * @return the score category
     */
    public IScoreCategory getScoreCategoryFromRow(int row) {
        return rowToCategoryMap.get(row);
    }

    private void addHeaderRow(int row) {
        textMap.put(new CellPosition(row, 0), "Kategori🎲");
        for (int i = 0; i < players.size(); i++) {
            textMap.put(new CellPosition(row, i + 1), players.get(i).getName());
        }
    }

    private int addCategoryRows(int startRow, boolean upperSection) {
        int row = startRow;
        for (IScoreCategory category : categories) {
            if (category.isUpperCategory() == upperSection) {
                textMap.put(new CellPosition(row, 0), category.getDisplayName());
                rowToCategoryMap.put(row, category);
                for (int col = 1; col <= players.size(); col++) {
                    IPlayer player = players.get(col - 1);
                    int score = player.getScoreCard().getScore(category);
                    boolean used = player.getScoreCard().isCategoryUsed(category);
                    String text = !used ? "" : score == 0 ? "-" : String.valueOf(score);
                    textMap.put(new CellPosition(row, col), text);
                }
                row++;
            }
        }
        return row;
    }

    private int addUpperSumRow(int row) {
        textMap.put(new CellPosition(row, 0), "Sum");
        for (int col = 1; col <= players.size(); col++) {
            int sum = players.get(col - 1).getScoreCard().getUpperSectionScore();
            textMap.put(new CellPosition(row, col), String.valueOf(sum));
        }
        return row + 1;
    }

    private int addBonusRow(int row) {
        textMap.put(new CellPosition(row, 0), "Bonus");
        for (int col = 1; col <= players.size(); col++) {
            int bonus = players.get(col - 1).getScoreCard().getBonusScore();
            textMap.put(new CellPosition(row, col), bonus == 0 ? "-" : String.valueOf(bonus));
        }
        return row + 1;
    }

    private int addTotalRow(int row) {
        textMap.put(new CellPosition(row, 0), "Totalt");
        for (int col = 1; col <= players.size(); col++) {
            int total = players.get(col - 1).getScoreCard().getTotalScore();
            textMap.put(new CellPosition(row, col), String.valueOf(total));
        }
        return row + 1;
    }

    private CellPosition getCellFromMouse(int x, int y) {
        if (converter == null)
            return null;

        int rows = categories.length + NUM_EXTRA_ROWS;
        int cols = players.size() + 1;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                CellPosition pos = new CellPosition(row, col);
                Rectangle2D bounds = converter.getBoundsForCell(pos);
                if (bounds.contains(x, y))
                    return pos;
            }
        }

        return null;
    }

    private boolean isBoldRow(String rowHeader) {
        return rowHeader != null && Set.of("Kategori🎲", "Sum", "Bonus", "Totalt").contains(rowHeader);
    }

    private boolean isGrayBackgroundRow(String rowHeader) {
        return rowHeader != null && Set.of("Kategori🎲", "Totalt", "Sum").contains(rowHeader);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (textMap == null || players == null || categories == null)
            return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(YatzyStyle.COLOR_BG);
        g2.fillRect(0, 0, getWidth(), getHeight());

        Rectangle2D boardArea = new Rectangle2D.Double(
                OUTERMARGIN, OUTERMARGIN,
                Math.max(getWidth() - 2.0 * OUTERMARGIN, (players.size() + 1) * MIN_COL_WIDTH),
                getHeight() - 2.0 * OUTERMARGIN);

        g2.setColor(Color.WHITE);
        g2.fill(boardArea);

        converter = new CellPositionToPixelConverter(
                boardArea,
                new GridDimension() {
                    @Override
                    public int rows() {
                        return categories.length + NUM_EXTRA_ROWS;
                    }

                    @Override
                    public int cols() {
                        return players.size() + 1;
                    }
                },
                MARGIN);

        for (Map.Entry<CellPosition, String> entry : textMap.entrySet()) {
            CellPosition pos = entry.getKey();
            String text = entry.getValue();
            String rowHeader = textMap.get(new CellPosition(pos.row(), 0));
            Rectangle2D bounds = converter.getBoundsForCell(pos);

            g2.setColor(isGrayBackgroundRow(rowHeader) ? YatzyStyle.COLOR_CELL_HEADER : YatzyStyle.COLOR_CELL_BG);
            g2.fill(bounds);

            g2.setColor(YatzyStyle.COLOR_TEXT_DARK);
            g2.setFont(isBoldRow(rowHeader) ? YatzyStyle.FONT_SCORE_BOLD : YatzyStyle.FONT_SCORE_PLAIN);
            g2.draw(bounds);
            Inf101Graphics.drawCenteredString(g2, text, bounds);
        }
    }
}