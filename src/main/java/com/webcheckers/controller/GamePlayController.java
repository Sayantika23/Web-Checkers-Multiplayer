package com.webcheckers.controller;

import com.webcheckers.model.Board;

/**
 * The Class GamePlayController.
 */
public class GamePlayController {
	
	/** The board. */
	private Board board;

	/**
	 * Instantiates a new game play controller.
	 */
	public GamePlayController() {
		
	}
	
	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Sets the board.
	 *
	 * @param board the new board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
}
