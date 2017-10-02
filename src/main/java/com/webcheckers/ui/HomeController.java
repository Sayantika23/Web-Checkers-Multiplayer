package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;

import com.webcheckers.controller.GuiController;
import com.webcheckers.model.Button;
import com.webcheckers.model.Game;

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
	private Game game;
	public HomeController(Game game) {
		this.game = game;
	}
	GuiController guiController = game.getGUIController();
	@Override
	public ModelAndView handle(Request request, Response response) {;
	    Map<String, Object> vm = new HashMap<>();
	    Button button = guiController.getHomeSignupButton();
		vm.put("buttonClass", button.getButtonClass());
		vm.put("buttonType", button.getButtonType());
		vm.put("buttonText", button.getButtonText());
	    vm.put("title", "Welcome!");
	    return new ModelAndView(vm , "home.ftl");
  }

}