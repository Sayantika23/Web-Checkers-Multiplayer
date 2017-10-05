package com.webcheckers.model;

import java.util.ArrayList;

import javax.swing.text.Position;

public class Board implements GUI {
	
	public ArrayList<Row> iterator;
	
	public Board() {
		iterator = new ArrayList<Row>();
	}

	public ArrayList<Row> iterator() {
		for (int i = 0; i < 8; i++) {
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
