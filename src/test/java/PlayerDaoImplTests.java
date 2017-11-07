import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Human;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The Class PlayerDaoImplTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class PlayerDaoImplTests {

    private PlayerDaoImpl playerDaoImpl;
    private Human player;
    private Human opponent;

    @Before
    public void setup() throws IOException {
    	this.playerDaoImpl = new PlayerDaoImpl();
    	this.player = new Human();
    	this.opponent = new Human();
    }
 
    @After
    public void destroy() {
    	this.playerDaoImpl = null;
    }

    /**
     * Player is saved via DAO
     */
    @Test
    public void PlayerIsSaved() {
        player.setUsername("abc");
        player.setPassword("wxyz");
        playerDaoImpl.savePlayer(player);
        assertNotNull("Player is saved", playerDaoImpl.findPlayerByUsername(player.getUsername()));
        assertEquals(true, playerDaoImpl.passwordsMatch(player));

        player.setUsername("abc");
        player.setPassword("xyz");
        assertEquals(false, playerDaoImpl.passwordsMatch(player));
    }
    
    /**
     * Player Status is saved and deleted if exists via DAO
     */
    @Test
    public void PlayerStatusIsSavedAndDeleted() throws IOException {
        player.setUsername("signintest1");
        playerDaoImpl.savePlayerStatus(player, true);

        player.setUsername("signintest");
        playerDaoImpl.savePlayerStatus(player, true);

        assertNotNull("Player Status is saved", playerDaoImpl.getPlayersQueue(player));

        List<String> playersQueue = playerDaoImpl.getPlayersQueue(player);
        if(playersQueue.size()>0){
            for(int i=0;i<playersQueue.size();i++){
                player.setUsername(playersQueue.get(i));
                playerDaoImpl.deletePlayerStatus(player);
            }
        }

        player.setUsername("signintest");
        playerDaoImpl.deletePlayerStatus(player);
    }

    /**
     * Player Request is deleted if exists via DAO
     */
    @Test
    public void PlayerRequestIsSavedAndDeleted() throws IOException {
        player.setUsername("signintest");
        opponent.setUsername("signintest1");
        playerDaoImpl.requestOpponent(player, opponent);

        assertNotNull("Player Status is saved", playerDaoImpl.checkRequest(player));
        assertEquals(1, playerDaoImpl.checkRequest(opponent).size());

        playerDaoImpl.deletePlayerRequest(player, opponent);
        playerDaoImpl.deletePlayerRequests(player);
        playerDaoImpl.deletePlayerRequests(opponent);

        BufferedReader br = new BufferedReader(new FileReader("database/player_game_request.txt"));
        assertNull("Player Requests is deleted", br.readLine());
    }


    /**
     * Player Request is deleted if exists via DAO
     */
    @Test
    public void PlayerOpponentsIsSavedAndDeleted() throws IOException {
        player.setUsername("signintest");
        opponent.setUsername("signintest1");
        playerDaoImpl.requestOpponent(player, opponent);
        playerDaoImpl.registerOpponent(opponent, player);

        assertNotNull("Player opponent is saved", playerDaoImpl.checkRequestAcceptance(player, opponent));
        assertEquals(true, playerDaoImpl.checkRequestAcceptance(player, opponent));

        playerDaoImpl.deletePlayerOpponentRecords(player);
        playerDaoImpl.deletePlayerOpponentRecords(opponent);
    }

}