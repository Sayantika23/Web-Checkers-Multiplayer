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

	private static Board board;
	private Player player;
	private PlayerService playerService;
	private int redScore = 0;
	private int blackScore = 0;
	private String currentTurn;
	private String mutedColor;
	private final static int RED = 1;
	private final static int BLACK = 3;

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

	public Route postBoardRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
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

				return JsonUtils.toJson(jsonObject);
			}
		};
	}

	public Route postRemovePieceRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				// response.type("application/json");
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
			public Object handle(Request request, Response response) throws Exception {
				int score = player.getScore();
				score += 1;
				player.setScore(score);
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("score", score);
				return JsonUtils.toJson(jsonObject);
			}
		};
	}
	
	public Route postCheckTurnRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				String color = request.queryParams("color");
				setMutedColor(color);
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("turn", getMutedColor());
				return JsonUtils.toJson(jsonObject);
			}
		};
	}

	public Route getCheckTurnRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("turn", getMutedColor());
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
	public void setBoard(Board checkerboard) {
		board = checkerboard;
	}

	public int getBlackScore() {
		return blackScore;
	}

	public void setBlackScore(int blackScore) {
		this.blackScore = blackScore;
	}

	public int getRedScore() {
		return redScore;
	}

	public void setRedScore(int redScore) {
		this.redScore = redScore;
	}

	public void setCurrentPlayer(Player player) {
		this.player = player;
	}

	public Player getCurrentPlayer() {
		return player;
	}

	public void setMutedColor(String color) {
		this.mutedColor = color;
	}
	
	private String getMutedColor() {
		return mutedColor;
	}
}
