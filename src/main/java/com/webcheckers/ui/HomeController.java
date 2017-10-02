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
public class HomeController implements TemplateViewRoute {
	Game game = new Game();
	GUIController guiController = new GUIController(game);
	@Override
	public ModelAndView handle(Request request, Response response) {;
	    Map<String, Object> vm = new HashMap<>();
	    Button button = guiController.getHomeSignupButton();
	    Menu menu = guiController.getGameMenu();
		vm.put("buttonClass", button.getButtonClass());
		vm.put("buttonType", button.getButtonType());
		vm.put("buttonText", button.getButtonText());
	    vm.put("title", "Welcome!");
	    return new ModelAndView(vm , "home.ftl");
  }

}