package com.webcheckers.model;

import javax.swing.text.Position;

public class Square implements GUI {
	
	private int cellId;
	private boolean isValidSquare;
	public Checker piece;
	
	public Square(int cellId, Checker piece, boolean isValidSquare) {
		this.cellId = cellId;
		this.piece = piece;
		this.isValidSquare = isValidSquare;
	}

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
		
	}
	
	public int getCellId() {
		return cellId;
	}
	
	public boolean isValid() {
		return isValidSquare;
	}
	
	public Checker getPiece() {
		return this.piece;
	}

}
