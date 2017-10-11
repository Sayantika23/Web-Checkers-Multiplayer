package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.GuiController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Button;
import com.webcheckers.model.Game;
import com.webcheckers.model.Menu;
import com.webcheckers.model.Player;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import spark.Session;

/**
 * The Web Controller for the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GameController implements TemplateViewRoute {

	static final String GAME_VIEW_NAME = "game.ftl";
	static final String TITLE = "title";
	static final String PLAYER_NAME = "playerName";
	static final String OPPONENT_NAME = "opponentName";
	static final String PLAYER_COLOR = "playerColor";
	static final String OPPONENT_COLOR = "opponentColor";
	static final String MY_TURN = "isMyTurn";
	static final String BOARD = "board";
	static final String PLAYER_ONE_SCORE = "playerOneScore";
	static final String PLAYER_TWO_SCORE = "playerTwoScore";
	static final String INVALID_ACCESS_MESSAGE = "You must be registered and signed in to play.";
	private GuiController guiController;
	private Board board;
	private Menu gameMenu;
	private String viewName;
	static final String NEW_SESSION_ATTR = "newSession";
	private GamePlayController gamePlayController;

	public GameController(Game game) {
		Objects.requireNonNull(game, "game must not be null");
		this.guiController = game.getGUIController();
		this.gamePlayController = game.getGamePlayController();
		this.gameMenu = guiController.getGameMenu();
		this.board = new Board();
		gamePlayController.setBoard(board);
	}

	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();
		Session session = request.session();
		final Player player = session.attribute("player");
		if (player == null) {
			Button button = new GuiController().getHomeSigninButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
			vm.put(HomeController.LOGIN_STATUS, true);
			vm.put(HomeController.LOGIN_MESSAGE, INVALID_ACCESS_MESSAGE);
			vm.put(HomeController.NEW_USER, false);
			vm.put(HomeController.LOGIN_PAGE, true);
			vm.put(HomeController.SIGNUP_STATUS, false);
			vm.put(HomeController.SIGNUP_MESSAGE, null);
			viewName = HomeController.HOME_VIEW_NAME;
		} else {
			Button button = guiController.getGameSignoutButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(TITLE, "Game Page");
			vm.put(PLAYER_NAME, "Player One");
			vm.put(OPPONENT_NAME, "Player Two");
			vm.put(PLAYER_COLOR, "black");
			vm.put(OPPONENT_COLOR, "red");
			vm.put(MY_TURN, false);
			vm.put(BOARD, board);
			vm.put(PLAYER_ONE_SCORE, gameMenu.getPlayerOneScore());
			vm.put(PLAYER_TWO_SCORE, gameMenu.getPlayerTwoScore());
			viewName = GAME_VIEW_NAME;
		}
		return new ModelAndView(vm, viewName);
	}
}