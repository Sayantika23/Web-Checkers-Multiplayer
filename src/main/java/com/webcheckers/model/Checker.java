package com.webcheckers.model;

/**
 * The Class Checker.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Checker {
	
	private String type;
	private String colorClass;
	private String dataColor;

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
