package com.webcheckers.model;

import javax.swing.text.Position;

public class Checker implements GUI {
	
	private Integer cellId;
	private String type;
	private String color;

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
	}

	public Checker(int cellId, String type, String color) {
		this.cellId = cellId;
		this.type = type;
		this.color = color;
	}
	
	public int getCellId() {
		return cellId;
	}
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
}
