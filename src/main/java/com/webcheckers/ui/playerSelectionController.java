package com.webcheckers.ui;

import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.PlayerController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Button;
import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.service.PlayerService;
import spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class playerSelectionController.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class playerSelectionController implements TemplateViewRoute {

	/** The game view name. */
	final String PLAYER_SELECTION_VIEW_NAME = "playerlist.ftl";

	/** The player controller. */
	private PlayerController playerController;
	
	/** The gui controller. */
	private GuiController guiController;
	
	/** The view name. */
	private String viewName;

	/** players list. */
	private String PLAYER_LIST = "players";

	/**
	 * Instantiates a new post signin controller.
	 *
	 * @param game the game
	 */
	public playerSelectionController(Game game) {
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

		Session session = request.session();
		Human player = session.attribute("player");
		List<String> players = playerService.getPlayersQueue(player);

		Button button = guiController.getSelectButton();
		vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
		vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
		vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
		vm.put(GameController.TITLE, "Web Checkers");
		vm.put(PLAYER_LIST, players);
		viewName = PLAYER_SELECTION_VIEW_NAME;
		return new ModelAndView(vm, viewName);
	}
}
