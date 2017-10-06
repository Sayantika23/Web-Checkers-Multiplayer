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
	private final String CHECKER_TYPE = "checker";
	private final int PLAYER_ONE_HOME_ROW = 7;
	private final int PLAYER_TWO_HOME_ROW = 0;

	public Row(int rowNumber) {
		this.rowNumber = rowNumber;
		iterator = new ArrayList<Square>();
	}

	public ArrayList<Square> iterator() {
		Square square = null;
		Checker checker = null;
		for (int i = 0; i < 8; i++) {
			checker = new Checker();
			if (rowNumber == PLAYER_TWO_HOME_ROW) {
				if ((i & 1) != 0) {
					checker.setColorClass(RED_CHECKER_COLOR_CLASS);
					checker.setDataColor(RED_CHECKER_DATA_COLOR);
				} else {
					checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
					checker.setDataColor(TRANSPARENT_CHECKER_DATA_COLOR);
				}
			} else if (rowNumber == 1) {
				if ((i & 1) == 0) {
					checker.setColorClass(RED_CHECKER_COLOR_CLASS);
					checker.setDataColor(RED_CHECKER_DATA_COLOR);
				} else {
					checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
					checker.setDataColor(TRANSPARENT_CHECKER_DATA_COLOR);
				}
			} else if (rowNumber == 6) {
				if ((i & 1) != 0) {
					checker.setColorClass(WHITE_CHECKER_COLOR_CLASS);
					checker.setDataColor(WHITE_CHECKER_DATA_COLOR);
				} else {
					checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
					checker.setDataColor(TRANSPARENT_CHECKER_DATA_COLOR);
				}
			} else if (rowNumber == PLAYER_ONE_HOME_ROW) {
				if ((i & 1) == 0) {
					checker.setColorClass(WHITE_CHECKER_COLOR_CLASS);
					checker.setDataColor(WHITE_CHECKER_DATA_COLOR);
				} else {
					checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
					checker.setDataColor(TRANSPARENT_CHECKER_DATA_COLOR);
				}
			} else {
				checker.setColorClass(TRANSPARENT_CHECKER_COLOR_CLASS);
				checker.setDataColor(TRANSPARENT_CHECKER_DATA_COLOR);
			}
			checker.setType(CHECKER_TYPE);
			square = new Square(i, checker);
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
