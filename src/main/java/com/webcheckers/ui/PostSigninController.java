package com.webcheckers.ui;

import com.webcheckers.controller.PlayerController;
import com.webcheckers.controller.GuiController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Button;
import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
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

	/** The game view name. */
	final String GAME_VIEW_NAME = "game.ftl";
	
	/** The login view name. */
	final String LOGIN_VIEW_NAME = "home.ftl";
	
	/** The Constant USER_NAME. */
	static final String USER_NAME = "inputUsername";
	
	/** The Constant PASSWORD. */
	static final String PASSWORD = "inputPassword";
	
	/** The Constant INVALID_LOGIN_MESSAGE. */
	static final String INVALID_LOGIN_MESSAGE = "Incorrect Username/Password";
	
	/** The player controller. */
	private PlayerController playerController;
	
	/** The gui controller. */
	private GuiController guiController;
	
	/** The view name. */
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

	/* (non-Javadoc)
	 * @see spark.TemplateViewRoute#handle(spark.Request, spark.Response)
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
			Button button = guiController.getGameSignoutButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(GameController.TITLE, "Web Checkers");
			vm.put(GameController.PLAYER_NAME, "Player One");
			vm.put(GameController.OPPONENT_NAME, "Player Two");
			vm.put(GameController.PLAYER_COLOR, "white");
			vm.put(GameController.OPPONENT_COLOR, "red");
			vm.put(GameController.MY_TURN, false);
			vm.put(GameController.BOARD, new Board());
			vm.put(GameController.PLAYER_ONE_SCORE, guiController.getGameMenu().getPlayerOneScore());
			vm.put(GameController.PLAYER_TWO_SCORE, guiController.getGameMenu().getPlayerTwoScore());
			viewName = GAME_VIEW_NAME;
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
