package com.webcheckers.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardModel;
import com.webcheckers.model.Human;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;
import com.webcheckers.ui.JsonUtils;
import com.webcheckers.ui.MoveJsonUtils;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static spark.Spark.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The Class GamePlayController.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class GamePlayController {

	/** The board. */
	private static Board board;
	
	private Player player;

	PlayerService playerService;

	/**
	 * Instantiates a new game play controller.
	 */
	public GamePlayController() {
		try {
			playerService = new PlayerService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new MoveJsonUtils();
	}
	
	public void setCurrentPlayer(Player currentPlayer) {
		this.player = currentPlayer;
	}

	public Route postBoardRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
//				response.type("application/json");
				String boardJson = request.queryParams("model");
				
				JsonArray jsonArray = JsonUtils.fromJson(boardJson, JsonArray.class);

				JsonElement originalPosition = jsonArray.get(0);
				JsonArray array1 = originalPosition.getAsJsonArray();
				String vector1 = array1.get(0).getAsString();
				String color1 = array1.get(1).getAsString();

				int color = 0;
				switch (color1) {
				case "RED":
					color = 1;
					break;
				case "BLACK":
					color = 3;
					break;
				}

				String[] vectors1 = vector1.split(",");
				int currRow = Integer.parseInt(vectors1[0]);
				int currCol = Integer.parseInt(vectors1[1]);

				JsonElement newPosition = jsonArray.get(1);
				JsonArray array2 = newPosition.getAsJsonArray();
				String vector2 = array2.get(0).getAsString();
				// String color2 = array1.get(1).getAsString();

				String[] vectors2 = vector2.split(",");
				int moveRow = Integer.parseInt(vectors2[0]);
				int moveCol = Integer.parseInt(vectors2[1]);

				Move move = new Move(currRow, currCol, moveRow, moveCol);
				board.setPlayer(color);

				boolean validMove = board.isValidMove(move);
				
				if (validMove) {
					board.movePiece(move);
				}
				
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("valid", validMove);
				System.out.println("VALID: " + validMove);
				
				return JsonUtils.toJson(jsonObject);
			}
		};
	}
	
	public Route postRemovePieceRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
//				response.type("application/json");
				String boardJson = request.queryParams("model");
				
				JsonArray jsonArray = JsonUtils.fromJson(boardJson, JsonArray.class);

				JsonElement originalPosition = jsonArray.get(0);
				JsonArray array1 = originalPosition.getAsJsonArray();
				String vector1 = array1.get(0).getAsString();
				String color1 = array1.get(1).getAsString();

				int color = 0;
				switch (color1) {
				case "RED":
					color = 1;
					break;
				case "BLACK":
					color = 3;
					break;
				}

				String[] vectors1 = vector1.split(",");
				int currRow = Integer.parseInt(vectors1[0]);
				int currCol = Integer.parseInt(vectors1[1]);

//				JsonElement newPosition = jsonArray.get(1);
//				JsonArray array2 = newPosition.getAsJsonArray();
//				String vector2 = array2.get(0).getAsString();
//				// String color2 = array1.get(1).getAsString();
//
//				String[] vectors2 = vector2.split(",");
//				int moveRow = Integer.parseInt(vectors2[0]);
//				int moveCol = Integer.parseInt(vectors2[1]);

				Move move = new Move(currRow, currCol, currRow, currCol);
				board.setPlayer(color);

				board.removePiece(move);
				
				return null;
			}
		};
	}
	
	public Route postScoreRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {;
				Player currentPlayer = playerService.findPlayer(player);
				int updatedScore = currentPlayer.getScore() + 1;
				currentPlayer.setScore(updatedScore);
//				playerService.updateScore(currentPlayer);
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("score", updatedScore);
				return JsonUtils.toJson(jsonObject);
			}
		};
	}

	public static Route getLegalMovesRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {

				return null;
			}
		};
	}

	public static ArrayList<Move> getLegalMoves(int row, int col) {
		return board.getLegalMovesForPlayer(row, col);
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
	 * @param board
	 *            the new board
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
}
