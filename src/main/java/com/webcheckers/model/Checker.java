package com.webcheckers.model;

import javax.swing.text.Position;

public class Checker implements GUI {
	
	private String type;
	private String colorClass;
	private String dataColor;

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
	}

	public Checker() {
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getColorClass() {
		return colorClass;
	}

	public void setColorClass(String colorClass) {
		this.colorClass = colorClass;
	}

	public String getDataColor() {
		return dataColor;
	}

	public void setDataColor(String dataColor) {
		this.dataColor = dataColor;
	}
}
