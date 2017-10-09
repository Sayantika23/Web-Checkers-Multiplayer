package com.webcheckers.controller;

import com.webcheckers.model.Board;

public class GamePlayController {
	
	private Board board;

	public GamePlayController() {
		
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
