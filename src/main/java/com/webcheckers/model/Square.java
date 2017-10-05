package com.webcheckers.model;

import javax.swing.text.Position;

public class Square implements GUI {
	
	private int index;
	private boolean spaceIsValid = true;
	public Checker piece;
	
	public Square(int index, Checker piece) {
		this.index = index;
		this.piece = piece;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getCellId() {
		return index;
	}	
	
	public boolean isValid() {
		return spaceIsValid;
	}

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
		
	}

}
