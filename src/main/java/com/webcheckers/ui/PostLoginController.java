package com.webcheckers.ui;

import com.webcheckers.controller.PlayerController;
import com.webcheckers.controller.GuiController;
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
public class PostLoginController implements TemplateViewRoute {

	static final String GAME_VIEW_NAME = "game.ftl";
	static final String LOGIN_VIEW_NAME = "home.ftl";
	static final String USER_NAME = "inputUsername";
	static final String PASSWORD = "inputPassword";
	static final String INVALID_LOGIN_MESSAGE = "Incorrect Username/Password";
	private PlayerController playerController;
	private GuiController guiController;

	public PostLoginController(Game game) {
		playerController = game.getPlayerController();
		guiController = game.getGUIController();
	}

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
			vm.put(GameController.TITLE, "Web Checkers");
			vm.put(GameController.PLAYER_NAME, "Player One");
			vm.put(GameController.OPPONENT_NAME, "Player Two");
			vm.put(GameController.PLAYER_COLOR, "white");
			vm.put(GameController.OPPONENT_COLOR, "red");
			vm.put(GameController.MY_TURN, false);
			vm.put(GameController.BOARD, new Board());
			vm.put(GameController.PLAYER_ONE_SCORE, guiController.getGameMenu().getPlayerOneScore());
			vm.put(GameController.PLAYER_TWO_SCORE, guiController.getGameMenu().getPlayerTwoScore());
			return new ModelAndView(vm, GAME_VIEW_NAME);
		} else {
			Button button = new GuiController().getHomeLoginButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(HomeController.TITLE, "Invalid Login");
			vm.put(HomeController.LOGIN_STATUS, true);
			vm.put(HomeController.LOGIN_MESSAGE, INVALID_LOGIN_MESSAGE);
			vm.put(HomeController.NEW_USER, false);
			vm.put(HomeController.SIGNUP_MESSAGE, null);
			return new ModelAndView(vm, LOGIN_VIEW_NAME);
		}
	}
}
