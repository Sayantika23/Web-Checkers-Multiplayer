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

	/** The Constant GAME_VIEW_NAME. */
	static final String GAME_VIEW_NAME = "game.ftl";
	
	/** The Constant TITLE. */
	static final String TITLE = "title";
	
	/** The Constant PLAYER_NAME. */
	static final String PLAYER_NAME = "playerName";
	
	static final String SCORE = "score";
	
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
	
	static final String RED_TOP_CHECKER = "redTopChecker";

	public static final String INVALID_ACCESS_MESSAGE = "You must be registered and signed in to play.";

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
	
	private final static int RED = 1;
	
	private final static int BLACK = 3;

	private boolean redTopChecker = false;
	
    static final String TITLE_ATTRIBUTE = "title";
    static final String SELECT_PLAYER_NAME = "player";
    static final String PLAYER_LIST = "players";
    static final String COMPUTER_LEVELS = "levels";
    static final String IS_HUMAN = "is_human";
    static final String REQUESTS = "invites";
    private ArrayList<String> opponents = new ArrayList<String>();
    private boolean initialized = false;

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
		final String selectedOpponent = request.queryParams("opponentName");
		final String opponentType = request.queryParams("opponentType");
		final String requestType = request.queryParams("requestType");
	
		Session session = request.session();
		final Player player = session.attribute("player");
		
		if (!initialized) {
			switch(player.getColor()) {
				case "RED": {
					board.setPlayer(RED);
//					redTopChecker = true;
				}
				break;
				case "BLACK": {
					board.setPlayer(BLACK);
//					redTopChecker = false;
				}
				break;
			}
			board.initializeGame();
			board.createBoardIterator();
			initialized = true;
		}
		
		
		Player currentPlayer = null;
		if (gamePlayController.getCurrentPlayer() == null) {
			gamePlayController.setCurrentPlayer(player);
			currentPlayer = player;
		} else {
			currentPlayer = gamePlayController.getCurrentPlayer();
		}
		PlayerService playerService = playerController.getPlayerService();
		boolean accepted = false;
		Player opponent = new Human();
		
		// If human is selected as opponent
		if(opponentType.equals("human")){
			opponent.setUsername(selectedOpponent);
			if(playerService.checkRequestAcceptance(currentPlayer, opponent)){
				if(requestType.equals("invite")){
					playerService.registerOpponent(currentPlayer, opponent);
					accepted = true;
				} else if(requestType.equals("request")){
					playerService.requestOpponent(currentPlayer, opponent);
				}
			} else {
				accepted = true;
			}
		} else{
			opponent.setUsername("Computer");
			accepted = true;
		}
		if (currentPlayer == null) {
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
	        List<String> players = playerService.getPlayersQueue(currentPlayer);

	        // Get invitations available for current player
	        List<String> invites = playerService.checkRequest(currentPlayer);
	        vm.put(REQUESTS, invites);

	        Button button = guiController.getSelectButton();
	        vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
	        vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
	        vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
	        vm.put(TITLE_ATTRIBUTE, TITLE);

	        if(request.queryParams(PLAYER_NAME) == null) {
	             opponent1 = "human";
	        } else {
	             opponent1 = request.queryParams(PLAYER_NAME);
	        }

	        if(opponent1.equals("human")) {
	            // Display the list of available players
	            vm.put(PLAYER_LIST, players);
	            vm.put(IS_HUMAN, true);
	        } else{
	            // Provide Easy and Hard level option if User choose to play against computer
	            List<String> computerLevel = new ArrayList<>();
	            computerLevel.add("Easy");
	            computerLevel.add("Hard");
	            vm.put(COMPUTER_LEVELS, computerLevel) ;
	            vm.put(IS_HUMAN, false);
	        }
			viewName = PlayerSelectionController.PLAYER_LIST_VIEW;
		} else {
			Button button = guiController.getGameSignoutButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(TITLE, "Game Page");
			vm.put(OPPONENT_ASSIGNED, accepted);
			vm.put(PLAYER_NAME, currentPlayer.getUsername());
			vm.put(OPPONENT_NAME, opponent.getUsername());
			vm.put(PLAYER_COLOR, "black");
			vm.put(OPPONENT_COLOR, "red");
			vm.put(MY_TURN, false);
			vm.put(BOARD, board);
//			vm.put(RED_TOP_CHECKER, redTopChecker);
			vm.put(PLAYER_ONE_SCORE, gameMenu.getPlayerOneScore());
			vm.put(PLAYER_TWO_SCORE, gameMenu.getPlayerTwoScore());
			vm.put(SCORE, currentPlayer.getScore());
			viewName = GAME_VIEW_NAME;
		}
		return new ModelAndView(vm, viewName);
	}
}