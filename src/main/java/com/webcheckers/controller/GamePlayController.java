package com.webcheckers.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.ui.JsonUtils;
import com.webcheckers.ui.MoveJsonUtils;

import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

/**
 * The Class GamePlayController.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class GamePlayController {

	private static Board board;
	private int redScore = 0;
	private int blackScore = 0;
	private String mutedColor;
	public static ArrayList<Player> playerList = new ArrayList<Player>();

	/**
	 * Instantiates a new game play controller
	 * and new instance of json utils for
	 * serializing/deserializing player
	 * moves
	 */
	public GamePlayController() {
		new MoveJsonUtils();
	}

	/**
	 * Post board route.
	 *
	 * Submit post route from the game 
	 * page to update checkerboard array 
	 * and return boolean to indicate whether 
	 * or not the move was valid
	 * 
	 * @return boolean for valid move
	 */
	public Route postBoardRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				String json = request.queryParams("model");
				JsonObject moveObject = JsonUtils.fromJson(json, JsonObject.class);
				int currRow = moveObject.get("startRow").getAsInt();
				int currCol = moveObject.get("moveRow").getAsInt();
				int moveRow = moveObject.get("startRow").getAsInt();
				int moveCol = moveObject.get("moveRow").getAsInt();
				String checkerColor = moveObject.get("color").getAsString();
				
				int color = 0;
				switch (checkerColor) {
				case "RED":
					color = 1;
					break;
				case "BLACK":
					color = 3;
					break;
				}
				
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

	/**
	 * Post remove piece route.
	 *
	 * Post request from the game page parses 
	 * array vectors and removes captured
	 * piece from the checkerboard array
	 * 
	 * @return null
	 */
	public Route postRemovePieceRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {			
				String json = request.queryParams("model");
				JsonObject moveObject = JsonUtils.fromJson(json, JsonObject.class);
				int currRow = moveObject.get("startRow").getAsInt();
				int currCol = moveObject.get("startCol").getAsInt();
				String checkerColor = moveObject.get("color").getAsString();
				
				int color = 0;
				switch (checkerColor) {
				case "RED":
					color = 1;
					break;
				case "BLACK":
					color = 3;
					break;
				}
				
				Move move = new Move(currRow, currCol, currRow, currCol);
				board.setPlayer(color);

				board.removePiece(move);
				
				return null;
			}
		};
	}

	/**
	 * Post score route.
	 * 
	 * Post request updates score in the 
	 * gameplay controller and returns updated
	 * scores for each player from the player
	 * list
	 *
	 * @return the json string for current scores
	 */
	public Route postScoreRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				String color = request.queryParams("color");
				int score = 0;
				Player playerOne = playerList.get(0);
				Player playerTwo = playerList.get(1);
				if (playerOne.getColor().equals(color)) {
					score = playerOne.getScore();
					score += 1;
					playerOne.setScore(score);
					playerList.set(0, playerOne);
				} else {
					if (playerTwo != null) {
						if (playerTwo.getColor().equals(color)) {
							score = playerTwo.getScore();
							score += 1;
							playerTwo.setScore(score);
							playerList.set(1, playerTwo);
						}
					}
				}

				JsonObject jsonObject = new JsonObject();
				JsonObject playerScore = new JsonObject();
				playerScore.addProperty("playerOne", playerOne.getScore());
				if (playerTwo != null) {
					playerScore.addProperty("playerTwo", playerTwo.getScore());
				}
				jsonObject.add("score", playerScore);
				return JsonUtils.toJson(jsonObject);
			}
		};
	}
	


	/**
	 * Get score route.
	 *
	 * Gets scores from players stored in
	 * player list when the user refreshes
	 * the page
	 * 
	 * @return the json string for scores
	 */
	public Route getScoreRoute() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Player playerOne = playerList.get(0);
				Player playerTwo = null;
				if (playerList.size() > 1) {
					playerTwo = playerList.get(1);
				}
				JsonObject jsonObject = new JsonObject();
				JsonObject playerScore = new JsonObject();
				playerScore.addProperty("playerOne", playerOne.getScore());
				if (playerTwo != null) {
					playerScore.addProperty("playerTwo", playerTwo.getScore());
				} else {
					playerScore.addProperty("playerTwo", 0);
				}
				jsonObject.add("score", playerScore);
				return JsonUtils.toJson(jsonObject);
			}
		};
	}

	/**
	 * Post check turn route.
	 *
	 * Post request from the game page sets color in gameplay controller to mute
	 * after each player makes their checkers moves
	 * 
	 * @return the json string for current turn
	 */
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

	/**
	 * Gets the check turn route.
	 * 
	 * Get request checks which checker color 
	 * turn it is when the user refreshes the
	 * game page
	 *
	 * @return the json string for current turn
	 */
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

	public static ArrayList<Move> getLegalMoves(int row, int col) {
		return board.getLegalMovesForPlayer(row, col);
	}

	public Board getBoard() {
		return board;
	}

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

	public void setMutedColor(String color) {
		this.mutedColor = color;
	}

	private String getMutedColor() {
		return mutedColor;
	}
}
