package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
	
	/** The Constant HOME_VIEW_NAME. */
	static final String HOME_VIEW_NAME = "home.ftl";
	
	/** The Constant TITLE. */
	static final String TITLE = "Web Checkers";
	
	/** The Constant TITLE_ATTRIBUTE. */
	static final String TITLE_ATTRIBUTE = "title";
	
	/** The Constant BUTTON_CLASS. */
	static final String BUTTON_CLASS = "buttonClass";
	
	/** The Constant BUTTON_TYPE. */
	static final String BUTTON_TYPE = "buttonType";
	
	/** The Constant BUTTON_TEXT. */
	static final String BUTTON_TEXT = "buttonText";
	
	/** The Constant LOGIN_STATUS. */
	static final String LOGIN_STATUS = "loginFail";
	
	/** The Constant SIGNUP_STATUS. */
	static final String SIGNUP_STATUS = "signupFail";
	
	/** The Constant LOGIN_MESSAGE. */
	static final String LOGIN_MESSAGE = "message";
	
	/** The Constant LOGIN_PAGE. */
	static final String LOGIN_PAGE = "signinPage";
	
	/** The Constant NEW_USER. */
	static final String NEW_USER = "newUserSignup";
	
	/** The Constant SIGNUP_MESSAGE. */
	static final String SIGNUP_MESSAGE = "SignUpMessage";
	
	/** The gui controller. */
	private GuiController guiController;
	
	/**
	 * Instantiates a new home controller.
	 *
	 * @param game the game
	 */
	public HomeController(Game game) {
		Objects.requireNonNull(game, "game must not be null");
		this.guiController = game.getGUIController();
	}

	/* (non-Javadoc)
	 * @see spark.TemplateViewRoute#handle(spark.Request, spark.Response)
	 */
	@Override
	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();
		Button button = guiController.getHomeSigninButton();
		vm.put(BUTTON_CLASS, button.getButtonClass());
		vm.put(BUTTON_TYPE, button.getButtonType());
		vm.put(BUTTON_TEXT, button.getButtonText());
		vm.put(TITLE_ATTRIBUTE, TITLE);
		vm.put(LOGIN_STATUS, false);
		vm.put(SIGNUP_STATUS, false);
		vm.put(LOGIN_MESSAGE, "Welcome");
		vm.put(LOGIN_PAGE, true);
		vm.put(NEW_USER, false);
		vm.put(SIGNUP_MESSAGE, false);
		return new ModelAndView(vm, HOME_VIEW_NAME);
	}
}