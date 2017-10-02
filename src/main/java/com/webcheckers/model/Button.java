package com.webcheckers.model;

import javax.swing.text.Position;
public class Button implements GUI {
	private String buttonClass;
	  
	@Override
	public void draw(Position position) {
		
	}
	public String getButtonClass() {
		return buttonClass;
	}
	public void setButtonClass(String buttonClass) {
		this.buttonClass = buttonClass;
	}

}
