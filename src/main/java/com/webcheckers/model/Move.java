package com.webcheckers.model;

import javafx.util.Pair;

/**
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class Move {
    public int currRow, currCol, movRow, movCol;

    /**
     * Creates a new move
     * @param currRow the current row of the piece
     * @param currCol the current column of the piece
     * @param movRow the row the piece will be moved to
     * @param movCol the column the piece will be moved to
     */
    public Move(int currRow, int currCol, int movRow, int movCol) {
        this.currRow = currRow;
        this.currCol = currCol;
        this.movRow = movRow;
        this.movCol = movCol;
    }

    /**
     * Returns the location of the space in between the move
     * @return the row, col location of the space
     */
    public Pair<Integer, Integer> getSpaceInbetween() {
        return new Pair<>((currRow + movRow) / 2, (currCol + movCol) / 2);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Move)) {
            return false;
        }

        Move other = (Move) obj;

        return currRow == other.currRow && currCol == other.currCol && movRow == other.movRow && movCol == other.movCol;
    }
}