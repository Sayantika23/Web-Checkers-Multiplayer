package com.webcheckers.ui;

import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.PlayerController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Button;
import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.service.PlayerService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class PostSignupController implements TemplateViewRoute {

	static final String LOGIN_VIEW_NAME = "home.ftl";
	static final String USER_NAME = "inputUsername";
	static final String PASSWORD = "inputPassword";
	static final String EMAIL = "inputEmail";
	static final String FIRST_NAME = "inputFirstName";
	static final String LAST_NAME = "inputLastName";
	static final String SIGNUP_SUCCESS_MESSAGE = "You have successfully signed up.";
	static final String SIGNUP_FAIL_MESSAGE = "Error while signing up.";
	private PlayerController playerController;
	private GuiController guiController;

	public PostSignupController(Game game) {
		playerController = game.getPlayerController();
		guiController = game.getGUIController();
	}

	@Override
	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();
		PlayerService playerService = playerController.getPlayerService();

		final String username = request.queryParams(USER_NAME);
		final String password = request.queryParams(PASSWORD);
		final String message = SIGNUP_SUCCESS_MESSAGE;

		Human player = new Human();
		player.setUsername(username);
		player.setPassword(password);
		playerService.savePlayer(player);

		Button button = guiController.getHomeLoginButton();
		vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
		vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
		vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
		vm.put(HomeController.TITLE, "Welcome!");
		vm.put(HomeController.LOGIN_STATUS, false);
		vm.put(HomeController.LOGIN_MESSAGE, null);
		vm.put(HomeController.NEW_USER, true);
		vm.put(HomeController.SIGNUP_MESSAGE, message);
		return new ModelAndView(vm, LOGIN_VIEW_NAME);
	}
}
