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


    public PlayerSelectionController(Game game) {
        this.game = game;
        Objects.requireNonNull(game, "game must not be null");
        this.guiController = game.getGUIController();
        this.playerController = game.getPlayerController();
    }

    @Override
    public ModelAndView handle(Request request, Response response) {
        Map<String, Object> vm = new HashMap<>();
        PlayerService playerService = playerController.getPlayerService();

        Session session = request.session();
        Player player = session.attribute("player");

        List<String> players = playerService.getPlayersQueue(player);
        List<String> computerLevel = new ArrayList<>();
        computerLevel.add("Easy");
        computerLevel.add("Hard");

        final String opponent = request.queryParams(PLAYER_NAME);

        List<String> invites = playerService.checkRequest(player);
        Button button = guiController.getSelectButton();
        vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
        vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
        vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
        vm.put(TITLE_ATTRIBUTE, TITLE);
        vm.put(REQUESTS, invites);
        if(opponent.equals("human")) {
            vm.put(PLAYER_LIST, players);
            vm.put(IS_HUMAN, true);
        }
        else{
            vm.put(COMPUTER_LEVELS, computerLevel) ;
            vm.put(IS_HUMAN, false);
        }
        return new ModelAndView(vm, PLAYER_LIST_VIEW);
    }
}