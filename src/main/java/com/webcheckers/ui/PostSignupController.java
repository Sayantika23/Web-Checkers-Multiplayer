package com.webcheckers.ui;

import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.PlayerController;
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
import java.util.Map;

/**
 * The Class PostSignupController.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class PostSignupController implements TemplateViewRoute {

	/** The Constant LOGIN_VIEW_NAME. */
	static final String LOGIN_VIEW_NAME = "home.ftl";
	
	/** The Constant USER_NAME. */
	static final String USER_NAME = "inputUsername";
	
	/** The Constant PASSWORD. */
	static final String PASSWORD = "inputPassword";
	
	/** The Constant EMAIL. */
	static final String EMAIL = "inputEmail";
	
	/** The Constant FIRST_NAME. */
	static final String FIRST_NAME = "inputFirstName";
	
	/** The Constant LAST_NAME. */
	static final String LAST_NAME = "inputLastName";
	
	/** The Constant SIGNUP_SUCCESS_MESSAGE. */
	static final String SIGNUP_SUCCESS_MESSAGE = "You have successfully signed up.";
	
	/** The Constant SIGNUP_FAILURE_MESSAGE. */
	static final String SIGNUP_FAILURE_MESSAGE = "Username is taken. Try again.";
	
	/** The Constant SIGNUP_FAIL_MESSAGE. */
	static final String SIGNUP_FAIL_MESSAGE = "Error while signing up.";
	
	/** The player controller. */
	private PlayerController playerController;
	
	/** The gui controller. */
	private GuiController guiController;

	private Game game;

	/**
	 * Instantiates a new post signup controller.
	 *
	 * @param game the game
	 */
	public PostSignupController(Game game) {
		playerController = game.getPlayerController();
		guiController = game.getGUIController();
		this.game = game;
	}

	/* (non-Javadoc)
	 * @see spark.TemplateViewRoute#handle(spark.Request, spark.Response)
	 */
	@Override
	public ModelAndView handle(Request request, Response response) {
		
		Session session = request.session();
		session.attribute("player", null);
		
		Map<String, Object> vm = new HashMap<>();
		PlayerService playerService = playerController.getPlayerService();

		final String username = request.queryParams(USER_NAME);
		final String password = request.queryParams(PASSWORD);
		String signupMessage;
		boolean signupStatus;
		boolean newUserSignup;
		boolean signInPage;

		Human player = new Human();
		player.setUsername(username);
		player.setPassword(password);
		player.setScore(0);
		Player existingPlayer = playerService.findPlayer(player);
		Player controllerPlayer;
		
		if (existingPlayer == null) {
			playerService.savePlayer(player);
			controllerPlayer = player;
			signupStatus = false;
			newUserSignup = true;
			signInPage = true;
			signupMessage = SIGNUP_SUCCESS_MESSAGE;
		} else {
			controllerPlayer = existingPlayer;
			signupStatus = true;
			newUserSignup = false;
			signInPage = false;
			signupMessage = SIGNUP_FAILURE_MESSAGE;
		}
		
		game.getGamePlayController().setCurrentPlayer(controllerPlayer);
		
		Button button = guiController.getHomeSigninButton();
		vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
		vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
		vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
		vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
		vm.put(HomeController.LOGIN_STATUS, false);
		vm.put(HomeController.LOGIN_PAGE, signInPage);
		vm.put(HomeController.LOGIN_MESSAGE, signupMessage);
		vm.put(HomeController.NEW_USER, newUserSignup);
		vm.put(HomeController.SIGNUP_STATUS, signupStatus);
		vm.put(HomeController.SIGNUP_MESSAGE, signupMessage);
		return new ModelAndView(vm, LOGIN_VIEW_NAME);
	}
}
