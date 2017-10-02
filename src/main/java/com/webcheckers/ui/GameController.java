package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;

import com.webcheckers.controller.GUIController;
import com.webcheckers.model.Button;
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
	Game game = new Game();
	GUIController guiController = new GUIController(game);
	@Override
	public ModelAndView handle(Request request, Response response) {;
	    Map<String, Object> vm = new HashMap<>();
	    vm.put("title", "Game Page");
	    vm.put("playerName", "Player One");
	    vm.put("opponentColor", "red");
	    vm.put("playerColor", "black");
	    return new ModelAndView(vm , "game.ftl");
  }

}