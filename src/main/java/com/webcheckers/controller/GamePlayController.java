package com.webcheckers.controller;

import com.webcheckers.model.Board;
import com.webcheckers.model.BoardModel;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static spark.Spark.*;

/**
 * The Class GamePlayController.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class GamePlayController {
	
	/** The board. */
	private Board board;

	/**
	 * Instantiates a new game play controller.
	 */
	public GamePlayController() {
		
	}
	
	public static Route getRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				String boardJson = request.queryParams("model");
				return null;
			}
		};
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
