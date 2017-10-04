package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;

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
	public GameController(Game game) {
		guiController = new GuiController();
		board = new Board();
	}
	@Override
	public ModelAndView handle(Request request, Response response) {;
	    Map<String, Object> vm = new HashMap<>();
	    Menu gameMenu = guiController.getGameMenu();
	    vm.put("title", "Game Page");
	    vm.put("playerName", "Player One");
	    vm.put("opponentName", "Player Two");
	    vm.put("playerColor", "black");
	    vm.put("opponentColor", "red");
	    vm.put("isMyTurn", false);
	    vm.put("board", board);
	    vm.put("playerOneScore", gameMenu.getPlayerOneScore());
	    vm.put("playerTwoScore", gameMenu.getPlayerTwoScore());
	    return new ModelAndView(vm , "game.ftl");
  }

}