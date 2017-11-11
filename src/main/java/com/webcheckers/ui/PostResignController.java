package com.webcheckers.ui;

import com.webcheckers.dao.PlayerDao;
import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

public class PostResignController implements  Route {
    public PostResignController(Game game) {
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Player player = session.attribute("player");
       // final String selectedOpponent = request.queryParams("opponentName");


            /** The player dao impl. */
         PlayerDaoImpl playerDaoImpl = new PlayerDaoImpl();

        playerDaoImpl.deletePlayerStatus(player); // delete the status
        playerDaoImpl.savePlayerStatus(player, false); //change Player status to false
        playerDaoImpl.deletePlayerRequests(player);
        playerDaoImpl.deletePlayerOpponentRecords(player);

        //the ajax will receive data from this class which the  ajax will redirect to mode page
        //since the Player status is false, the other player should win the game.
        return null;
    }
}
