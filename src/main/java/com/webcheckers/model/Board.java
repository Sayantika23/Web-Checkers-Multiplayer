package com.webcheckers.model;

import java.util.ArrayList;

/**
 * The Class Board.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Board {
	
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
}
