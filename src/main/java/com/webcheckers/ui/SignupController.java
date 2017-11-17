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
 * The Class SignupController.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class SignupController implements TemplateViewRoute {
	
	private GuiController guiController;

	/**
	 * Instantiates a new signup controller.
	 *
	 * @param game the game
	 */
	public SignupController(Game game) {
		guiController = game.getGUIController();
	}

	 /**
     * Model and view handler
     * 
     * @param request
     * @param response
     * @return new model and view
     */
	@Override
	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();
		Button button = guiController.getHomeSignupButton();
		vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
		vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
		vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
		vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
		vm.put(HomeController.LOGIN_STATUS, false);
		vm.put(HomeController.SIGNUP_STATUS, false);
		vm.put(HomeController.LOGIN_MESSAGE, "Welcome");
		vm.put(HomeController.LOGIN_PAGE, false);
		vm.put(HomeController.NEW_USER, false);
		vm.put(HomeController.SIGNUP_MESSAGE, false);
		return new ModelAndView(vm, HomeController.HOME_VIEW_NAME);
	}
}