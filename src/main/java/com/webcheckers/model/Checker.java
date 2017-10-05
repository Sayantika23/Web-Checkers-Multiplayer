package com.webcheckers.model;

import javax.swing.text.Position;

public class Checker implements GUI {
	
	private Integer cellId;
	public String type;
	public String color;

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

	public void setType(String type) {
		this.type = type;
		
	}

	public void setColor(String color) {
		this.color = color;
	}
	


}
