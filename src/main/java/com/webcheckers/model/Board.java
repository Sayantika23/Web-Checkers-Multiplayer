package com.webcheckers.model;

import java.util.ArrayList;

/**
 * The Class Board.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Board {

	static final int
			EMPTY = 0,           // Value representing an empty square.
			RED = 1,             // A regular red piece.
			RED_KING = 2,        // A red king.
			BLACK = 3,           // A regular black piece.
			BLACK_KING = 4;      // A black king.

	/** The iterator. */
	public ArrayList<Row> iterator;
	
	/** The number of rows. */
	private final static int NUMBER_OF_ROWS = 8;
	/** The number of columns. */
	private final static int NUMBER_OF_COLS = 8;

	public static final int[][] board = new int[NUMBER_OF_ROWS][NUMBER_OF_COLS];

	/**
	 * Instantiates a new board.
	 */
	public Board() {
		this.iterator = new ArrayList<Row>();
		initializeGame();
	}

	/**
	 * Iterator.
	 *
	 * @return the array list
	 */
	public ArrayList<Row> iterator() {
		iterator.clear();
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			Row row = new Row(i, board[i]);
			iterator.add(row);
		}
		return iterator;
	}

	void initializeGame() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if ( row % 2 == col % 2 ) {
					if (row < 3)
						board[row][col] = BLACK;
					else if (row > 4)
						board[row][col] = RED;
					else
						board[row][col] = EMPTY;
				}
				else {
					board[row][col] = EMPTY;
				}
			}
		}
	}
}
