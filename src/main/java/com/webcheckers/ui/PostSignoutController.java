package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.PlayerController;
import com.webcheckers.model.Button;
import com.webcheckers.model.Game;

import com.webcheckers.model.Human;
import com.webcheckers.service.PlayerService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

/**
 * The Class PostSignoutController.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class PostSignoutController implements TemplateViewRoute {

	/**
	 * Static constants
	 */
	static final String TITLE = "Web Checkers";
	static final String TITLE_ATTRIBUTE = "title";
	static final String BUTTON_CLASS = "buttonClass";
	static final String BUTTON_TYPE = "buttonType";
	static final String BUTTON_TEXT = "buttonText";
	static final String LOGIN_STATUS = "loginFail";
	static final String SIGNUP_STATUS = "signupFail";
	static final String LOGIN_MESSAGE = "message";
	static final String LOGIN_PAGE = "signinPage";
	static final String NEW_USER = "newUserSignup";
	
	static final String SIGNUP_MESSAGE = "SignUpMessage";
	private GuiController guiController;
	private PlayerController playerController;
	private Game game;

	/**
	 * Instantiates a new post signout controller.
	 *
	 * @param game the game
	 */
	public PostSignoutController(Game game) {
		this.game = game;
		Objects.requireNonNull(game, "game must not be null");
		this.guiController = game.getGUIController();
		this.playerController = game.getPlayerController();
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
		Session session = request.session();
		PlayerService playerService = playerController.getPlayerService();
		Human player = session.attribute("player");
		playerService.deletePlayerStatus(player);
		playerService.deletePlayerRequests(player);
		playerService.deletePlayerOpponentRecords(player);
		session.attribute("player", null);
		game.cancelGame();
		Map<String, Object> vm = new HashMap<>();
		Button button = guiController.getHomeSigninButton();
		vm.put(BUTTON_CLASS, button.getButtonClass());
		vm.put(BUTTON_TYPE, button.getButtonType());
		vm.put(BUTTON_TEXT, button.getButtonText());
		vm.put(TITLE_ATTRIBUTE, TITLE);
		vm.put(LOGIN_STATUS, false);
		vm.put(SIGNUP_STATUS, false);
		vm.put(LOGIN_MESSAGE, HomeController.TITLE);
		vm.put(LOGIN_PAGE, true);
		vm.put(NEW_USER, false);
		vm.put(SIGNUP_MESSAGE, false);
		return new ModelAndView(vm, HomeController.HOME_VIEW_NAME);
	}
}