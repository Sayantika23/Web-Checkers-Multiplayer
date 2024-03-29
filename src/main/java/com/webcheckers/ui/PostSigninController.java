package com.webcheckers.ui;

import com.webcheckers.controller.PlayerController;
import com.webcheckers.controller.GuiController;
import com.webcheckers.model.*;
import com.webcheckers.service.PlayerService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class PostSigninController.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class PostSigninController implements TemplateViewRoute {

	/**
	 * Static constants
	 */
	final String PLAYER_SELECTION_VIEW_NAME = "playerselection.ftl";
	final String LOGIN_VIEW_NAME = "home.ftl";
	static final String USER_NAME = "inputUsername";
	static final String PASSWORD = "inputPassword";
	static final String INVALID_LOGIN_MESSAGE = "Incorrect Username/Password";
	
	private PlayerController playerController;
	private GuiController guiController;
	private String viewName;

	/**
	 * Instantiates a new post signin controller.
	 *
	 * @param game the game
	 */
	public PostSigninController(Game game) {
		playerController = game.getPlayerController();
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
		PlayerService playerService = playerController.getPlayerService();

		final String username = request.queryParams(USER_NAME);
		final String password = request.queryParams(PASSWORD);

		Human player = new Human();
		player.setUsername(username);
		player.setPassword(password);

		final boolean loginStatus = playerService.authenticate(player);

		if (loginStatus) {
			Session session = request.session();
			session.attribute("player", player);
			playerService.deletePlayerStatus(player);
			playerService.savePlayerStatus(player, true);
			Button button = guiController.getSelectButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(GameController.TITLE, "Web Checkers");
			viewName = PLAYER_SELECTION_VIEW_NAME;
		} else {
			Button button = new GuiController().getHomeSigninButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
			vm.put(HomeController.LOGIN_STATUS, true);
			vm.put(HomeController.LOGIN_MESSAGE, INVALID_LOGIN_MESSAGE);
			vm.put(HomeController.NEW_USER, false);
			vm.put(HomeController.LOGIN_PAGE, true);
			vm.put(HomeController.SIGNUP_STATUS, false);
			vm.put(HomeController.SIGNUP_MESSAGE, null);
			viewName = LOGIN_VIEW_NAME;
		}
		return new ModelAndView(vm, viewName);
	}
}