package com.webcheckers.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.webcheckers.controller.GuiController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.model.Menu;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

/**
 * The Web Controller for the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GameController implements TemplateViewRoute {
	
	static final String TITLE = "title";
	static final String PLAYER_NAME = "playerName";
	static final String OPPONENT_NAME = "opponentName";
	static final String PLAYER_COLOR = "playerColor";
	static final String OPPONENT_COLOR = "opponentColor";
	static final String MY_TURN = "isMyTurn";
	static final String BOARD = "board";
	static final String PLAYER_ONE_SCORE = "playerOneScore";
	static final String PLAYER_TWO_SCORE = "playerTwoScore";
	private GuiController guiController;
	private Board board;
	private Menu gameMenu;
	static String VIEW_NAME = "game.ftl";
	static final String NEW_SESSION_ATTR = "newSession";
	
	PlayerService playerService;

	public GameController(Game game) {
		Objects.requireNonNull(game, "game must not be null");
		this.guiController = game.getGUIController();
		this.gameMenu = guiController.getGameMenu();
		this.board = new Board();
		playerService = game.getPlayerController().getPlayerService();
	}

	public ModelAndView handle(Request request, Response response) {;
		
		Map<String, Object> vm = new HashMap<>();
		vm.put(TITLE, "Game Page");
		vm.put(PLAYER_NAME, "Player One");
		vm.put(OPPONENT_NAME, "Player Two");
		vm.put(PLAYER_COLOR, "black");
		vm.put(OPPONENT_COLOR, "red");
		vm.put(MY_TURN, false);
		vm.put(BOARD, board);
		vm.put(PLAYER_ONE_SCORE, gameMenu.getPlayerOneScore());
		vm.put(PLAYER_TWO_SCORE, gameMenu.getPlayerTwoScore());
		return new ModelAndView(vm, "game.ftl");
	}
}