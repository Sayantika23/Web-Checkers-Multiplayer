package com.webcheckers.model;

import javax.swing.text.Position;

public class Checker implements GUI {
	
	private Integer cellId;
	private boolean spaceIsValid = true;

	public Checker(int cellId) {
		this.cellId = cellId;
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

}
