package com.webcheckers.ui;

import java.util.*;

import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.PlayerController;
import com.webcheckers.model.*;
import com.webcheckers.service.PlayerService;
import spark.*;

/**
 * The Web Controller for the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class PlayerSelectionController implements TemplateViewRoute {

    static final String PLAYER_LIST_VIEW = "playerlist.ftl";
    static final String TITLE = "Web Checkers";
    static final String TITLE_ATTRIBUTE = "title";
    static final String PLAYER_NAME = "player";
    static final String PLAYER_LIST = "players";
    static final String COMPUTER_LEVELS = "levels";
    static final String IS_HUMAN = "is_human";
    static final String REQUESTS = "invites";
    private GuiController guiController;
    private Game game;

    /** The player controller. */
    private PlayerController playerController;


    /**
     * @param game
     */
    public PlayerSelectionController(Game game) {
        this.game = game;
        Objects.requireNonNull(game, "game must not be null");
        this.guiController = game.getGUIController();
        this.playerController = game.getPlayerController();
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    public ModelAndView handle(Request request, Response response) {
        Map<String, Object> vm = new HashMap<>();
        String opponent;

        PlayerService playerService = playerController.getPlayerService();

        Session session = request.session();
        Player player = session.attribute("player");

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

        if(request.queryParams(PLAYER_NAME) == null) {
        	opponent = "human";
        } else {
             opponent = request.queryParams(PLAYER_NAME);
        }

        if(opponent.equals("human")) {
            // Display the list of available players
            vm.put(PLAYER_LIST, players);
            vm.put(IS_HUMAN, true);
        }
        else{
            // Provide Easy and Hard level option if User choose to play against computer
            List<String> computerLevel = new ArrayList<>();
            computerLevel.add("Easy");
            computerLevel.add("Hard");
            vm.put(COMPUTER_LEVELS, computerLevel) ;
            vm.put(IS_HUMAN, false);
        }
        return new ModelAndView(vm, PLAYER_LIST_VIEW);
    }
}