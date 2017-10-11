package com.webcheckers.model;

import java.util.ArrayList;

import javax.swing.text.Position;

/**
 * The Class Board.
 */
public class Board implements GUI {
	
	/** The iterator. */
	public ArrayList<Row> iterator;
	
	/** The number of rows. */
	private final int NUMBER_OF_ROWS = 8;
	
	/**
	 * Instantiates a new board.
	 */
	public Board() {
		iterator = new ArrayList<Row>();
	}

	/**
	 * Iterator.
	 *
	 * @return the array list
	 */
	public ArrayList<Row> iterator() {
		iterator.clear();
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			Row row = new Row(i);
			iterator.add(row);
		}
		return iterator;
	}

	/* (non-Javadoc)
	 * @see com.webcheckers.model.GUI#draw(javax.swing.text.Position)
	 */
	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub	
	}
}
