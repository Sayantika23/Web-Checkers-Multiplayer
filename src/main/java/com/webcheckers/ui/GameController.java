package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.PlayerController;
import com.webcheckers.model.*;

import com.webcheckers.service.PlayerService;
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

	/** The Constant GAME_VIEW_NAME. */
	static final String GAME_VIEW_NAME = "game.ftl";
	
	/** The Constant TITLE. */
	static final String TITLE = "title";
	
	/** The Constant PLAYER_NAME. */
	static final String PLAYER_NAME = "playerName";
	
	/** The Constant OPPONENT_NAME. */
	static final String OPPONENT_NAME = "opponentName";
	
	/** The Constant PLAYER_COLOR. */
	static final String PLAYER_COLOR = "playerColor";
	
	/** The Constant OPPONENT_COLOR. */
	static final String OPPONENT_COLOR = "opponentColor";
	
	/** The Constant MY_TURN. */
	static final String MY_TURN = "isMyTurn";
	
	/** The Constant BOARD. */
	static final String BOARD = "board";
	
	/** The Constant PLAYER_ONE_SCORE. */
	static final String PLAYER_ONE_SCORE = "playerOneScore";
	
	/** The Constant PLAYER_TWO_SCORE. */
	static final String PLAYER_TWO_SCORE = "playerTwoScore";
	
	/** The Constant INVALID_ACCESS_MESSAGE. */
	static final String INVALID_ACCESS_MESSAGE = "You must be registered and signed in to play.";

	/** The Constant INVALID_ACCESS_MESSAGE. */
	static final String OPPONENT_ASSIGNED = "accepted";
	
	/** The gui controller. */
	private GuiController guiController;
	
	/** The board. */
	private Board board;
	
	/** The game menu. */
	private Menu gameMenu;
	
	/** The view name. */
	private String viewName;
	
	/** The Constant NEW_SESSION_ATTR. */
	static final String NEW_SESSION_ATTR = "newSession";
	
	/** The game play controller. */
	private GamePlayController gamePlayController;

	/** The player controller. */
	private PlayerController playerController;

	/**
	 * Instantiates a new game controller.
	 *
	 * @param game the game
	 */
	public GameController(Game game) {
		Objects.requireNonNull(game, "game must not be null");
		this.guiController = game.getGUIController();
		this.gamePlayController = game.getGamePlayController();
		this.gameMenu = guiController.getGameMenu();
		this.board = new Board();
		gamePlayController.setBoard(board);
		this.playerController = game.getPlayerController();
	}

	/* (non-Javadoc)
	 * @see spark.TemplateViewRoute#handle(spark.Request, spark.Response)
	 */
	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();
		Session session = request.session();
		final Player player = session.attribute("player");
		PlayerService playerService = playerController.getPlayerService();
		final String selectedOpponent = request.queryParams("opponentName");
		final String opponentType = request.queryParams("opponentType");
		final String requestName = request.queryParams("requestName");
		Human opponent = new Human();
		boolean accepted = false;

		if(opponentType==null){
			opponent.setUsername(requestName);
		}
		else{
			if(opponentType.equals("human")){
				opponent.setUsername(selectedOpponent);
			}
		}

		if (!(playerService.checkRequestAcceptance(player, opponent)) ) {
			if(opponentType==null){
				playerService.registerOpponent(player, opponent);
			}
			else{
				if(opponentType.equals("human")){
					playerService.requestOpponent(player, opponent);
				}
			}
		}

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
			vm.put(OPPONENT_ASSIGNED, playerService.checkRequestAcceptance(player, opponent));
			vm.put(PLAYER_NAME, player.getUsername());
			vm.put(OPPONENT_NAME, opponent.getUsername());
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