package com.webcheckers.model;

import javax.swing.text.Position;

public class Button implements GUI {
	
	private String buttonClass;
	private String buttonType;
	private String buttonText;
	  
	@Override
	public void draw(Position position) {
		
	}
	
	public String getButtonClass() {
		return buttonClass;
	}
	
	public void setButtonClass(String buttonClass) {
		this.buttonClass = buttonClass;
	}
	
	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	
	public String getButtonType() {
		return buttonType;
	}
	
	public String getButtonText() {
		return buttonText;
	}
	
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
}
