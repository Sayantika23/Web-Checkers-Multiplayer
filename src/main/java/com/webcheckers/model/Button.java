package com.webcheckers.model;

import javax.swing.text.Position;

/**
 * The Class Button.
 */
public class Button implements GUI {
	
	/** The button class. */
	private String buttonClass;
	
	/** The button type. */
	private String buttonType;
	
	/** The button text. */
	private String buttonText;
	  
	/* (non-Javadoc)
	 * @see com.webcheckers.model.GUI#draw(javax.swing.text.Position)
	 */
	@Override
	public void draw(Position position) {
		
	}
	
	/**
	 * Gets the button class.
	 *
	 * @return the button class
	 */
	public String getButtonClass() {
		return buttonClass;
	}
	
	/**
	 * Sets the button class.
	 *
	 * @param buttonClass the new button class
	 */
	public void setButtonClass(String buttonClass) {
		this.buttonClass = buttonClass;
	}
	
	/**
	 * Sets the button type.
	 *
	 * @param buttonType the new button type
	 */
	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	
	/**
	 * Gets the button type.
	 *
	 * @return the button type
	 */
	public String getButtonType() {
		return buttonType;
	}
	
	/**
	 * Gets the button text.
	 *
	 * @return the button text
	 */
	public String getButtonText() {
		return buttonText;
	}
	
	/**
	 * Sets the button text.
	 *
	 * @param buttonText the new button text
	 */
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
}
