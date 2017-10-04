package com.webcheckers.model;

import java.util.ArrayList;

import javax.swing.text.Position;

public class Board implements GUI {
	
	public ArrayList<Square> iterator;
	
	public Board() {
		iterator = new ArrayList<Square>();
	}

	public ArrayList<Square> iterator() {
		for (Integer i = 0; i < 64; i++) {
			Square square = new Square(i);
			iterator.add(square);
		}
		return iterator;
	}

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
		
	}

}
