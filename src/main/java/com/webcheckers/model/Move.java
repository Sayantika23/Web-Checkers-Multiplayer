package com.webcheckers.model;

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
}