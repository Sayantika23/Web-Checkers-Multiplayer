package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	/**
	 * Static constants
	 */
	static final String GAME_VIEW_NAME = "game.ftl";
	static final String TITLE = "title";
	static final String PLAYER_NAME = "playerName";
	static final String SCORE = "score";
	static final String OPPONENT_NAME = "opponentName";
	static final String PLAYER_COLOR = "playerColor";
	static final String OPPONENT_COLOR = "opponentColor";
	static final String MY_TURN = "isMyTurn";
	static final String BOARD = "board";
	static final String PLAYER_ONE_SCORE = "playerOneScore";
	static final String PLAYER_TWO_SCORE = "playerTwoScore";
	static final String RED_TOP_CHECKER = "redTopChecker";
	static final String SCORE_CLASS_ONE = "scoreClass1";
	static final String SCORE_CLASS_TWO = "scoreClass2";
	static final String RED_COLOR_CLASS = "red-score";
	static final String BLACK_COLOR_CLASS = "black-score";
	static final String RED_CURRENT_TURN = "RED";
	static final String BLACK_CURRENT_TURN = "BLACK";
	static final String PLAYER_ONE = "playerOne";
	static final String PLAYER_TWO = "playerTwo";
	static final String PLAYER_ONE_DIV_NAME = "playerOneDivName";
	static final String PLAYER_TWO_DIV_NAME = "playerTwoDivName";
	public static final String INVALID_ACCESS_MESSAGE = "You must be registered and signed in to play.";
	static final String OPPONENT_ASSIGNED = "accepted";
	static final String TITLE_ATTRIBUTE = "title";
	static final String SELECT_PLAYER_NAME = "player";
	static final String PLAYER_LIST = "players";
	static final String COMPUTER_LEVELS = "levels";
	static final String IS_HUMAN = "is_human";
	static final String REQUESTS = "invites";
	
	private String scoreClass1;
	private String scoreClass2;
	private String playerDivName1;
	private String playerDivName2;
	private GuiController guiController;
	private Board board;
	private String viewName;
	static final String NEW_SESSION_ATTR = "newSession";
	private GamePlayController gamePlayController;
	private PlayerController playerController;
	private final static int BLACK = 3;
	private Game game;

	/**
	 * Instantiates a new game controller.
	 *
	 * @param game
	 */
	public GameController(Game game) {
		this.game = game;
		Objects.requireNonNull(game, "game must not be null");
		this.guiController = game.getGUIController();
		this.gamePlayController = game.getGamePlayController();
		this.board = new Board();
		gamePlayController.setBoard(board);
		this.playerController = game.getPlayerController();
	}

	 /**
     * Model and view handler
     * 
     * @param request
     * @param response
     * @return new model and view
     */
	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();
		final String selectedOpponent = request.queryParams("opponentName");
		final String opponentType = request.queryParams("opponentType");
		final String requestType = request.queryParams("requestType");

		Session session = request.session();
		final Player player = session.attribute("player");

		if (player != null) {
			if (!game.isInitialized()) {
				GamePlayController.playerList.clear();
				game.initialize();
			}
			if (GamePlayController.playerList.isEmpty()) {
				GamePlayController.playerList.add(player);
				board.setPlayer(BLACK);
				gamePlayController.setMutedColor(RED_CURRENT_TURN);
				board.initializeGame();
				board.createBoardIterator();
			}
			if (GamePlayController.playerList.get(0).equals(player)) {
				player.setColor(BLACK_CURRENT_TURN);
				scoreClass1 = BLACK_COLOR_CLASS;
				scoreClass2 = RED_COLOR_CLASS;
				playerDivName1 = BLACK_CURRENT_TURN;
				playerDivName2 = RED_CURRENT_TURN;
			} else {
				if (GamePlayController.playerList.size() == 1) {
					GamePlayController.playerList.add(player);	
				}
				player.setColor(RED_CURRENT_TURN);
				scoreClass1 = RED_COLOR_CLASS;
				scoreClass2 = BLACK_COLOR_CLASS;
				playerDivName1 = RED_CURRENT_TURN;
				playerDivName2 = BLACK_CURRENT_TURN;
			}
		}

		PlayerService playerService = playerController.getPlayerService();
		boolean accepted = false;
		Player opponent = new Human();

		// If human is selected as opponent
		if (opponentType.equals("human")) {
			opponent.setUsername(selectedOpponent);
			if (playerService.checkRequestAcceptance(player, opponent)) {
				if (requestType.equals("invite")) {
					playerService.registerOpponent(player, opponent);
					accepted = true;
				} else if (requestType.equals("request")) {
					playerService.requestOpponent(player, opponent);
				}
			} else {
				accepted = true;
			}
		} else {
			opponent.setUsername("Computer");
			accepted = true;
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
		} else if (selectedOpponent == null & opponentType.equals("human")) {
			String opponent1;

			// Get the list of players to whom current player can send the request for play
			List<String> players = playerService.getPlayersQueue(player);

			// Get invitations available for current player
			List<String> invites = playerService.checkRequest(player);
			vm.put(REQUESTS, invites);

			Button button = guiController.getSelectButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(TITLE_ATTRIBUTE, TITLE);

			if (request.queryParams(PLAYER_NAME) == null) {
				opponent1 = "human";
			} else {
				opponent1 = request.queryParams(PLAYER_NAME);
			}

			if (opponent1.equals("human")) {
				// Display the list of available players
				vm.put(PLAYER_LIST, players);
				vm.put(IS_HUMAN, true);
			} else {
				// Provide Easy and Hard level option if User choose to play against computer
				List<String> computerLevel = new ArrayList<>();
				computerLevel.add("Easy");
				computerLevel.add("Hard");
				vm.put(COMPUTER_LEVELS, computerLevel);
				vm.put(IS_HUMAN, false);
			}
			viewName = PlayerSelectionController.PLAYER_LIST_VIEW;
		} else {
			String playerOneName = null;
			String playerTwoName = null;
			if (player.getColor() == "BLACK") {
				playerOneName = GamePlayController.playerList.get(0).getUsername();
				if (selectedOpponent == null) {
					playerTwoName = "Computer";
				} else  {
					playerTwoName = selectedOpponent;
				}
			} else if (player.getColor() == "RED") {
				playerOneName = GamePlayController.playerList.get(1).getUsername();
				playerTwoName = GamePlayController.playerList.get(0).getUsername();
			}
			Button button = guiController.getGameSignoutButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(TITLE, "Game Page");
			vm.put(OPPONENT_ASSIGNED, accepted);
			vm.put(PLAYER_NAME, playerOneName);
			vm.put(OPPONENT_NAME, playerTwoName);
			vm.put(PLAYER_COLOR, "black");
			vm.put(OPPONENT_COLOR, "red");
			vm.put(MY_TURN, false);
			vm.put(BOARD, board);
			vm.put(SCORE_CLASS_ONE, scoreClass1);
			vm.put(SCORE_CLASS_TWO, scoreClass2);
			vm.put(PLAYER_ONE_DIV_NAME, playerDivName1);
			vm.put(PLAYER_TWO_DIV_NAME, playerDivName2);
			viewName = GAME_VIEW_NAME;
		}
		return new ModelAndView(vm, viewName);
	}
}