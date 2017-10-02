package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;

import com.webcheckers.controller.GuiController;
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
	private Game game;
	public GameController(Game game) {
		this.game = game;
	}
	@Override
	public ModelAndView handle(Request request, Response response) {
		GuiController guiController = game.getGUIController();
		Menu menu = guiController.getGameMenu();
	    Map<String, Object> vm = new HashMap<>();
	    vm.put("title", "Game Page");
	    vm.put("playerName", "Player One");
	    vm.put("opponentColor", "red");
	    vm.put("playerColor", "black");
	    return new ModelAndView(vm , "game.ftl");
  }

}