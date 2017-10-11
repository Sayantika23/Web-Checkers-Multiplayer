package com.webcheckers.model;

import java.util.ArrayList;

import javax.swing.text.Position;

public class Board implements GUI {
	
	public ArrayList<Row> iterator;
	private final int NUMBER_OF_ROWS = 8;
	
	public Board() {
		iterator = new ArrayList<Row>();
	}

	public ArrayList<Row> iterator() {
		iterator.clear();
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			Row row = new Row(i);
			iterator.add(row);
		}
		return iterator;
	}

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub	
	}
}
