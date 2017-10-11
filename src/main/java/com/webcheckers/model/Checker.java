package com.webcheckers.model;

import javax.swing.text.Position;

/**
 * The Class Checker.
 */
public class Checker implements GUI {
	
	/** The type. */
	private String type;
	
	/** The color class. */
	private String colorClass;
	
	/** The data color. */
	private String dataColor;

	/* (non-Javadoc)
	 * @see com.webcheckers.model.GUI#draw(javax.swing.text.Position)
	 */
	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
	}

	/**
	 * Instantiates a new checker.
	 */
	public Checker() {
		
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the color class.
	 *
	 * @return the color class
	 */
	public String getColorClass() {
		return colorClass;
	}

	/**
	 * Sets the color class.
	 *
	 * @param colorClass the new color class
	 */
	public void setColorClass(String colorClass) {
		this.colorClass = colorClass;
	}

	/**
	 * Gets the data color.
	 *
	 * @return the data color
	 */
	public String getDataColor() {
		return dataColor;
	}

	/**
	 * Sets the data color.
	 *
	 * @param dataColor the new data color
	 */
	public void setDataColor(String dataColor) {
		this.dataColor = dataColor;
	}
}
