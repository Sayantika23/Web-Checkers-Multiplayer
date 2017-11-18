package com.webcheckers.model;

import java.util.ArrayList;

/**
 * The Class Row.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Row {

	public int rowNumber;
	public ArrayList<Square> iterator;
	private final String RED_CHECKER_COLOR_CLASS = "red";
	private final String BLACK_CHECKER_COLOR_CLASS = "black";
	private final String TRANSPARENT_CHECKER_COLOR_CLASS = "transparent";
	private final String RED_CHECKER_DATA_COLOR = "RED";
	private final String BLACK_CHECKER_DATA_COLOR = "BLACK";
	private final int NUMBER_OF_ROW_SQUARES = 8;
	private final String CHECKER = "SINGLE";
	private final String KING = "KING";
	private final String PLACEHOLDER = "placeholder";
	private int[] row = new int[NUMBER_OF_ROW_SQUARES];

	/**
	 * Instantiates a new row and
	 * creates new instance of square
	 * array list iterator
	 *
	 * @param rowNumber
	 */

	public Row(int rowNumber, int[] row) {
		this.rowNumber = rowNumber;
		this.row = row;
		iterator = new ArrayList<Square>();
	}

	/**
	 * Iterator.
	 * 
	 * Iterates through number of rows and sets
	 * initial placement of checkers with color
	 * class, data color and checker type 
	 *
	 * @return the array list of checker rows
	 */
	public ArrayList<Square> iterator() {
		Square square = null;
		Checker checker = null;
		boolean isValidSquare = false;
		for (int i = 0; i < NUMBER_OF_ROW_SQUARES; i++) {
			checker = new Checker();
			if (row[i] == Board.EMPTY) {
				checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
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

	public int getRowNumber() {
		return rowNumber;
	}
}
