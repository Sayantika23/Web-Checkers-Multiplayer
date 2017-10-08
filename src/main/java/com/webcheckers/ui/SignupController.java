package com.webcheckers.ui;

import com.webcheckers.controller.GuiController;
import com.webcheckers.model.Button;
import com.webcheckers.model.Game;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;


/**
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class SignupController implements TemplateViewRoute {
	
	private GuiController guiController;
	static final String VIEW_NAME = "signup.ftl";

	public SignupController(Game game) {
		guiController = game.getGUIController();
	}

	@Override
	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();
		Button button = guiController.getHomeSignupButton();
		vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
		vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
		vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
		vm.put("title", "Web Checkers");
		return new ModelAndView(vm, VIEW_NAME);
	}
}