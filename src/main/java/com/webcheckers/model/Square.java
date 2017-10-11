package com.webcheckers.model;

import javax.swing.text.Position;

/**
 * The Class Square.
 */
public class Square implements GUI {
	
	/** The cell id. */
	private int cellId;
	
	/** The is valid square. */
	private boolean isValidSquare;
	
	/** The piece. */
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

	/* (non-Javadoc)
	 * @see com.webcheckers.model.GUI#draw(javax.swing.text.Position)
	 */
	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Gets the cell id.
	 *
	 * @return the cell id
	 */
	public int getCellId() {
		return cellId;
	}
	
	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
		return isValidSquare;
	}
	
	/**
	 * Gets the piece.
	 *
	 * @return the piece
	 */
	public Checker getPiece() {
		return this.piece;
	}
}
