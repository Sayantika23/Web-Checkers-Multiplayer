package com.webcheckers.model;

import javax.swing.text.Position;

public class Square implements GUI {
	
	private int cellId;
	private boolean spaceIsValid = true;
	public Checker piece;
	
	public Square(int cellId, Checker piece) {
		this.cellId = cellId;
		this.piece = piece;
	}
	
	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
		
	}
	
	public int getCellId() {
		return cellId;
	}
	
	public boolean isValid() {
		return spaceIsValid;
	}
	
	public Checker getPiece() {
		return this.piece;
	}

}
