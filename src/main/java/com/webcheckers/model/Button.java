package com.webcheckers.model;

/**
 * The Class Button.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Button {
	
	private String buttonClass;
	private String buttonType;
	private String buttonText;
	
	public Button() {
		
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
