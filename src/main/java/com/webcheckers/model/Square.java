package com.webcheckers.model;

/**
 * The Class Square.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Square {
	
	private int cellId;
	private boolean isValidSquare;
	public Checker piece;
	
	/**
	 * Instantiates a new square.
	 *
	 * @param cellId the cell id
	 * @param piece the piece
	 * @param isValidSquare the is valid square
	 */
	public Square(int cellId, Checker piece, boolean isValidSquare) {
		this.cellId = cellId;
		this.piece = piece;
		this.isValidSquare = isValidSquare;
	}
	
	public int getCellId() {
		return cellId;
	}
	
	public boolean isValid() {
		return isValidSquare;
	}
	
	public Checker getPiece() {
		return this.piece;
	}
}
