package com.webcheckers.model;

import java.util.ArrayList;

import javax.swing.text.Position;

public class Row implements GUI {

	public int index;
	public ArrayList<Square> iterator;

	public Row(int index) {
		this.index = index;
		iterator = new ArrayList<Square>();
	}

	public ArrayList<Square> iterator() {
		Square square = null;
		Checker checker = null;
		int index = this.index;
		for (int i = 0; i < 8; i++) {
			if (index == 0 || index == 1) {
				checker = new Checker(i, "checker", "black");
			} else if (index == 6 || index == 7) {
				checker = new Checker(i, "checker", "red");
			}
			square = new Square(i, checker);
			iterator.add(square);

		}
		return iterator;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub

	}

}
