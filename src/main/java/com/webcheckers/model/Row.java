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
	private final String WHITE_CHECKER_COLOR_CLASS = "white";
	
	/** The transparent checker color class. */
	private final String TRANSPARENT_CHECKER_COLOR_CLASS = "transparent";
	
	/** The red checker data color. */
	private final String RED_CHECKER_DATA_COLOR = "RED";
	
	/** The white checker data color. */
	private final String WHITE_CHECKER_DATA_COLOR = "WHITE";
	
	/** The transparent checker data color. */
	private final String TRANSPARENT_CHECKER_DATA_COLOR = "TRANSPARENT";
	
	/** The player two home row. */
	private final int PLAYER_TWO_HOME_ROW = 0;
	
	/** The player one home row. */
	private final int PLAYER_ONE_HOME_ROW = 7;
	
	/** The number of row squares. */
	private final int NUMBER_OF_ROW_SQUARES = 8;
	
	/** The checker. */
	private final String CHECKER = "checker";
	
	/** The placeholder. */
	private final String PLACEHOLDER = "placeholder";

	/**
	 * Instantiates a new row.
	 *
	 * @param rowNumber the row number
	 */
	public Row(int rowNumber) {
		this.rowNumber = rowNumber;
		iterator = new ArrayList<Square>();
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
			if (rowNumber == PLAYER_TWO_HOME_ROW) {
				if ((i & 1) != 0) {
					checker.setColorClass(RED_CHECKER_COLOR_CLASS);
					checker.setDataColor(RED_CHECKER_DATA_COLOR);
					checker.setType(CHECKER);
					isValidSquare = true;
				} else {
					checker.setType(PLACEHOLDER);
					isValidSquare = false;
				}
			} else if (rowNumber == 1) {
				if ((i & 1) == 0) {
					checker.setColorClass(RED_CHECKER_COLOR_CLASS);
					checker.setDataColor(RED_CHECKER_DATA_COLOR);
					checker.setType(CHECKER);
					isValidSquare = true;
				} else {
					checker.setType(PLACEHOLDER);
					isValidSquare = false;
				}
			} else if (rowNumber == 2) {
				if ((i & 1) != 0) {
					checker.setColorClass(RED_CHECKER_COLOR_CLASS);
					checker.setDataColor(RED_CHECKER_DATA_COLOR);
					checker.setType(CHECKER);
					isValidSquare = true;
				} else {
					isValidSquare = false;
					checker.setType(PLACEHOLDER);
				}
			} else if (rowNumber == 3) {
				if ((i & 1) == 0) {
					checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
					checker.setDataColor(TRANSPARENT_CHECKER_DATA_COLOR);
					checker.setType(CHECKER);
					isValidSquare = true;
				} else {
					checker.setType(PLACEHOLDER);
					isValidSquare = false;
				}
			} else if (rowNumber == 4) {
				if ((i & 1) != 0) {
					checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
					checker.setDataColor(TRANSPARENT_CHECKER_DATA_COLOR);
					checker.setType(CHECKER);
					isValidSquare = true;
				} else {
					checker.setType(PLACEHOLDER);
					isValidSquare = false;
				}
			} else if (rowNumber == 5) {
				if ((i & 1) == 0) {
					checker.setColorClass(WHITE_CHECKER_COLOR_CLASS);
					checker.setDataColor(WHITE_CHECKER_DATA_COLOR);
					checker.setType(CHECKER);
					isValidSquare = true;
				} else {
					checker.setType(PLACEHOLDER);
					isValidSquare = false;
				}
			} else if (rowNumber == 6) {
				if ((i & 1) != 0) {
					checker.setColorClass(WHITE_CHECKER_COLOR_CLASS);
					checker.setDataColor(WHITE_CHECKER_DATA_COLOR);
					checker.setType(CHECKER);
					isValidSquare = true;
				} else {
					checker.setType(PLACEHOLDER);
					isValidSquare = false;
				}
			} else if (rowNumber == PLAYER_ONE_HOME_ROW) {
				if ((i & 1) == 0) {
					checker.setColorClass(WHITE_CHECKER_COLOR_CLASS);
					checker.setDataColor(WHITE_CHECKER_DATA_COLOR);
					checker.setType(CHECKER);
					isValidSquare = true;
				} else {
					checker.setType(PLACEHOLDER);
					isValidSquare = false;
				}
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
