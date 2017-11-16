package com.webcheckers.ui;

import com.webcheckers.controller.PlayerController;
import com.webcheckers.dao.PlayerDao;
import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;
import spark.*;

public class PostResignController implements  Route {
    /** The player controller. */
    private PlayerController playerController;


    public PostResignController(Game game) {
        this.playerController = game.getPlayerController();
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Player player = session.attribute("player");
       // final String selectedOpponent = request.queryParams("opponentName");


            /** The player dao impl. */
        PlayerService playerService = playerController.getPlayerService();

        playerService.deletePlayerStatus(player); // delete the status
        playerService.savePlayerStatus(player, false); //change Player status to false
        playerService.deletePlayerRequests(player);
        playerService.deletePlayerOpponentRecords(player);

        //the ajax will receive data from this class which the  ajax will redirect to mode page
        //since the Player status is false, the other player should win the game.
        return null;
    }
}
