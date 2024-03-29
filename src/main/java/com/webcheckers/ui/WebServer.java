package com.webcheckers.ui;

import static spark.Spark.*;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.JsonTransformer;
import com.webcheckers.model.EchoWebSocket;
import com.webcheckers.model.Game;
import spark.TemplateEngine;

/**
 * The server that initializes the set of HTTP request handlers. This defines
 * the <em>web application interface</em> for this guessing game application.
 *
 * <p>
 * There are multiple ways in which you can have the client issue a request and
 * the application generate responses to requests. If your team is not careful
 * when designing your approach, you can quickly create a mess where no one can
 * remember how a particular request is issued or the response gets generated.
 * Aim for consistency in your approach for similar activities or requests.
 * </p>
 *
 * <p>
 * Design choices for how the client makes a request include:
 * <ul>
 * <li>Request URL</li>
 * <li>HTTP verb for request (GET, POST, PUT, DELETE and so on)</li>
 * <li><em>Optional:</em> Inclusion of request parameters</li>
 * </ul>
 * </p>
 *
 * <p>
 * Design choices for generating a response to a request include:
 * <ul>
 * <li>View templates with conditional elements</li>
 * <li>Use different view templates based on results of executing the client
 * request</li>
 * <li>Redirecting to a different application URL</li>
 * </ul>
 * </p>
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class WebServer {

	/**
	 * Static constants
	 */
	
	public static final String GET_HOME_URL = "/";
	public static final String GET_GAME_URL = "/game";
	public static final String POST_SIGNIN_URL = "/game";
	public static final String GET_SIGNUP_URL = "/signup";
	public static final String POST_SIGNUP_URL = "/signup";
	public static final String POST_SIGNOUT_URL = "/";
	public static final String POST_UPDATE_BOARD_MODEL_URL = "/updateBoardModel";
	public static final String GET_BOARD_MODEL_URL = "/getBoardModel";
	public static final String POST_PLAYER_SCORE = "/updateScore";
	public static final String GET_PLAYER_SCORE = "/getScore";
	public static final String POST_REMOVE_PIECE = "/removePiece";
	public static final String POST_CHECK_TURN = "/checkTurn";
	public static final String GET_CHECK_TURN = "/getTurn";
	public static final String PLAYER_MODE_URL = "/mode";

	private final TemplateEngine templateEngine;
	private final Game game;

	/**
	 * The constructor for the Web Server.
	 *
	 * @param templateEngine
	 * The default {@link TemplateEngine} to render views.
	 */
	public WebServer(final TemplateEngine templateEngine, Game game) {
		this.templateEngine = templateEngine;
		this.game = game;
	}

	/**
	 * Initialize all of the HTTP routes that make up this web application.
	 *
	 * <p>
	 * Initialization of the web server includes defining the location for static
	 * files, and defining all routes for processing client requests. The method
	 * returns after the web server finishes its initialization.
	 * </p>
	 */
	public void initialize() {
		// Create new Game model to instantiate controllers

		// Configuration to serve static files
		staticFileLocation("/public");

		//// Setting any route (or filter) in Spark triggers initialization of the
		//// embedded Jetty web server.

		//// A route is set for a request verb by specifying the path for the
		//// request, and the function callback (request, response) -> {} to
		//// process the request. The order that the routes are defined is
		//// important. The first route (request-path combination) that matches
		//// is the one which is invoked. Additional documentation is at
		//// http://sparkjava.com/documentation.html and in Spark tutorials.

		//// Each route (processing function) will check if the request is valid
		//// from the client that made the request. If it is valid, the route
		//// will extract the relevant data from the request and pass it to the
		//// application object delegated with executing the request. When the
		//// delegate completes execution of the request, the route will create
		//// the parameter map that the response template needs. The data will
		//// either be in the value the delegate returns to the route after
		//// executing the request, or the route will query other application
		//// objects for the data needed.

		//// FreeMarker defines the HTML response using templates. Additional
		//// documentation is at
		//// http://freemarker.org/docs/dgui_quickstart_template.html.
		//// The Spark FreeMarkerEngine lets you pass variable values to the
		//// template via a map. Additional information is in online
		//// tutorials such as
		//// http://benjamindparrish.azurewebsites.net/adding-freemarker-to-java-spark/.

		//// These route definitions are examples. You will define the routes
		//// that are appropriate for the HTTP client interface that you define.
		//// Create separate Route classes to handle each route; this keeps your
		//// code clean; using small classes.

		// Creates websocket endpoint for JavaScript calls.
		webSocket("/update", EchoWebSocket.class);

		// Shows the Checkers game Home page.
		get(GET_HOME_URL, new HomeController(game), templateEngine);

		// Shows the Checkers game Game page.
		get(GET_GAME_URL, new GameController(game), templateEngine);

		// Shows the Checkers game Login page.
		post(POST_SIGNIN_URL, new PostSigninController(game), templateEngine);

		// Shows the Checkers game signup page.
		get(GET_SIGNUP_URL, new SignupController(game), templateEngine);

		// Shows the Checkers game signin and signup pages
		post(POST_SIGNUP_URL, new PostSignupController(game), templateEngine);

		// Shows the Checkers game home page.
		post(POST_SIGNOUT_URL, new PostSignoutController(game), templateEngine);

		// Shows the checkers game PlayerSelection page.
		post(PLAYER_MODE_URL, new PlayerSelectionController(game), templateEngine);

		// Shows the checkers game PlayerSelection page.
		get(PLAYER_MODE_URL, new PlayerSelectionController(game), templateEngine);

		// Updates the game board model from an AJAX call.
		post(POST_UPDATE_BOARD_MODEL_URL, game.getGamePlayController().postBoardRoute(), new JsonTransformer());

		// Updates player score from an AJAX call.
		post(POST_PLAYER_SCORE, game.getGamePlayController().postScoreRoute(), new JsonTransformer());

		// Gets player score from an AJAX call.
		get(GET_PLAYER_SCORE, game.getGamePlayController().getScoreRoute(), new JsonTransformer());

		// Removes captured piece from 2d board array from an AJAX call.
		post(POST_REMOVE_PIECE, game.getGamePlayController().postRemovePieceRoute(), new JsonTransformer());

		// Updates current player turn from an AJAX call.
		post(POST_CHECK_TURN, game.getGamePlayController().postCheckTurnRoute(), new JsonTransformer());

		// Gets current player turn from an AJAX call.
		get(GET_CHECK_TURN, game.getGamePlayController().getCheckTurnRoute(), new JsonTransformer());
	}
}