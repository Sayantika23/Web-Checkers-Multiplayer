package com.webcheckers.model;

import java.util.ArrayList;

import javax.swing.text.Position;

public class Row implements GUI {

	public int rowNumber;
	public ArrayList<Square> iterator;
	private final String RED_CHECKER_COLOR_CLASS = "red shadow";
	private final String WHITE_CHECKER_COLOR_CLASS = "white shadow";
	private final String TRANSPARENT_CHECKER_COLOR_CLASS = "transparent";
	private final String RED_CHECKER_DATA_COLOR = "RED";
	private final String WHITE_CHECKER_DATA_COLOR = "WHITE";
	private final String TRANSPARENT_CHECKER_DATA_COLOR = "TRANSPARENT";
	private final int PLAYER_TWO_HOME_ROW = 0;
	private final int PLAYER_ONE_HOME_ROW = 7;
	private final String CHECKER = "checker";
	private final String PLACEHOLDER = "placeholder";

	public Row(int rowNumber) {
		this.rowNumber = rowNumber;
		iterator = new ArrayList<Square>();
	}

	public ArrayList<Square> iterator() {
		Square square = null;
		Checker checker = null;
		boolean isValidSquare = false;
		for (int i = 0; i < 8; i++) {
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
					checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
					checker.setDataColor(TRANSPARENT_CHECKER_DATA_COLOR);
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
					checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
					checker.setDataColor(TRANSPARENT_CHECKER_DATA_COLOR);
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
					isValidSquare = true;
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

	public int getRowNumber() {
		return rowNumber;
	}

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub

	}

}
