package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.webcheckers.controller.GuiController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Menu;

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
	private GuiController guiController;
	private Board board;
	private Menu gameMenu;
	static String VIEW_NAME = "game.ftl";
	static final String NEW_SESSION_ATTR = "newSession";

	public GameController(Game game) {
		Objects.requireNonNull(game, "game must not be null");
		this.guiController = game.getGUIController();
		this.gameMenu = guiController.getGameMenu();
		this.board = new Board();
	}

	@Override
	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();
		vm.put("title", "Game Page");
		vm.put("playerName", "Player One");
		vm.put("opponentName", "Player Two");
		vm.put("playerColor", "black");
		vm.put("opponentColor", "red");
		vm.put("isMyTurn", false);
		vm.put("board", board);
		vm.put("playerOneScore", gameMenu.getPlayerOneScore());
		vm.put("playerTwoScore", gameMenu.getPlayerTwoScore());
		vm.put(NEW_SESSION_ATTR, true);
		return new ModelAndView(vm, VIEW_NAME);
	}

}