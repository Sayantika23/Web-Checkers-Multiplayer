package com.webcheckers.model;

import javax.swing.text.Position;

public class Square implements GUI {
	
	private int index;
	private boolean spaceIsValid = true;
	public Checker piece;
	
	public Square(int index, Checker checker) {
		this.index = index;
		this.piece = checker;
	}
	
	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
		
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
	
	public Checker getPiece() {
		return this.piece;
	}

}
