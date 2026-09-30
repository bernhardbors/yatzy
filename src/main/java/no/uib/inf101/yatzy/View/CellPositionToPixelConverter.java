package no.uib.inf101.yatzy.View;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.yatzy.grid.CellPosition;
import no.uib.inf101.yatzy.grid.GridDimension;

/**
 * A class for converting a cell position in a grid to a pixel position in a
 * given drawing area.
 * 
 * This class is used to calculate where each individual cell in the Tetris
 * board should be
 * placed on the screen, based on the dimensions of the board and a defined
 * area.
 */
public class CellPositionToPixelConverter {

    private final Rectangle2D box;
    private final GridDimension gd;
    private final double margin;

    /**
     * Creates a new converter for calculating pixel positions for cells in a grid.
     * 
     * @param box    The area (as a rectangle) where the board is drawn.
     * @param gd     Dimensions of the board (number of rows and columns).
     * @param margin Distance (in pixels) between cells.
     */
    public CellPositionToPixelConverter(Rectangle2D box, GridDimension gd, double margin) {
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }

    /**
     * Calculates the pixel position and size of a given cell in the grid.
     * 
     * @param cellPosition Position of the cell on the board (row and column).
     * @return A Rectangle2D-object representing the cellarea in pixels.
     */
    public Rectangle2D getBoundsForCell(CellPosition cellPosition) {
        double cellW = (box.getWidth() - margin * gd.cols() - margin) / gd.cols();
        double cellH = (box.getHeight() - margin * gd.rows() - margin) / gd.rows();
        double cellX = box.getX() + margin + (cellW + margin) * cellPosition.col();
        double cellY = box.getY() + margin + (cellH + margin) * cellPosition.row();
        return new Rectangle2D.Double(cellX, cellY, cellW, cellH);
    }

}
