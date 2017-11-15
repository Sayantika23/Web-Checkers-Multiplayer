package com.webcheckers.model;

import java.util.ArrayList;

/**
 * The Class Row.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Row {

	/** The row number. */
	public int rowNumber;

	/** The iterator. */
	public ArrayList<Square> iterator;

	/** The red checker color class. */
	private final String RED_CHECKER_COLOR_CLASS = "red";

	/** The white checker color class. */
	private final String BLACK_CHECKER_COLOR_CLASS = "black";

	/** The red checker data color. */
	private final String RED_CHECKER_DATA_COLOR = "RED";

	/** The white checker data color. */
	private final String BLACK_CHECKER_DATA_COLOR = "BLACK";

	/** The number of row squares. */
	private final int NUMBER_OF_ROW_SQUARES = 8;

	/** The checker. */
	private final String CHECKER = "SINGLE";

	private final String KING = "KING";

	/** The placeholder. */
	private final String PLACEHOLDER = "placeholder";

	private int[] row = new int[NUMBER_OF_ROW_SQUARES];

	/**
	 * Instantiates a new row.
	 *
	 * @param rowNumber
	 *            the row number
	 */
	public Row(int rowNumber) {
		this.rowNumber = rowNumber;
		iterator = new ArrayList<Square>();
	}

	public Row(int rowNumber, int[] row) {
		this.rowNumber = rowNumber;
		iterator = new ArrayList<Square>();
		this.row = row;
	}

	/**
	 * Iterator.
	 *
	 * @return the array list
	 */
	public ArrayList<Square> iterator() {
		Square square = null;
		Checker checker = null;
		boolean isValidSquare = false;
		for (int i = 0; i < NUMBER_OF_ROW_SQUARES; i++) {
			checker = new Checker();
			if (row[i] == Board.EMPTY) {
				checker.setType(PLACEHOLDER);
				isValidSquare = true;
			} else if (row[i] == Board.RED) {
				checker.setColorClass(RED_CHECKER_COLOR_CLASS);
				checker.setDataColor(RED_CHECKER_DATA_COLOR);
				checker.setType(CHECKER);
				isValidSquare = true;
			} else if (row[i] == Board.RED_KING) {
				checker.setColorClass(RED_CHECKER_COLOR_CLASS);
				checker.setDataColor(RED_CHECKER_DATA_COLOR);
				checker.setType(KING);
				isValidSquare = true;
			} else if (row[i] == Board.BLACK) {
				checker.setColorClass(BLACK_CHECKER_COLOR_CLASS);
				checker.setDataColor(BLACK_CHECKER_DATA_COLOR);
				checker.setType(CHECKER);
				isValidSquare = true;
			} else if (row[i] == Board.BLACK_KING) {
				checker.setColorClass(BLACK_CHECKER_COLOR_CLASS);
				checker.setDataColor(BLACK_CHECKER_DATA_COLOR);
				checker.setType(KING);
				isValidSquare = true;
			} else if (row[i] == Board.INVALID){
				checker.setType(PLACEHOLDER);
				isValidSquare = false;
			}
			square = new Square(i, checker, isValidSquare);
			iterator.add(square);
		}
		return iterator;
	}

	/**
	 * Gets the row number.
	 *
	 * @return the row number
	 */
	public int getRowNumber() {
		return rowNumber;
	}
}
