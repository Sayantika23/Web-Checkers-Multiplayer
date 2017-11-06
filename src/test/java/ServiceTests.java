import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.webcheckers.model.Human;
import com.webcheckers.model.Score;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.webcheckers.model.Game;
import com.webcheckers.service.PlayerService;

/**
 * The Class ServiceTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class ServiceTests {
	
	/** The game. */
	private Game game;

	private PlayerService playerService;

	private Human player;
	private Human opponent;
	/**
	 * Instantiates a new service tests.
	 */
	public ServiceTests() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setup() {
		this.playerService = game.getPlayerController().getPlayerService();
		this.player = new Human();
		this.opponent = new Human();
	}

	@After
	public void destroy() {
		this.playerService = null;
		this.game =  null;
		this.player  = null;
		this.opponent  = null;
	}
    /**
     * Player service should not be null.
     */
    @Test
    public void playerServiceShouldNotBeNull() {
    	assertNotNull("PlayerService must not be null", playerService);
    }

	/**
	 * Player service should handle authentication.
	 */
	@Test
	public void playerServiceShouldHandleAuthentication() {
		player.setUsername("");
		player.setPassword("");
		assertEquals(false, playerService.authenticate(player));

		//Test for no user with username abcxyz
		player.setUsername("abcxyz");
		assertEquals(false, playerService.authenticate(player));
	}

	/**
	 * Player is saved via DAO
	 */
	@Test
	public void PlayerIsSaved() {
		player.setUsername("abc");
		player.setPassword("wxyz");
		playerService.savePlayer(player);
		assertNotNull("Player is saved", playerService.findPlayer(player));
	}


	/**
	 * Player Status is saved and deleted if exists via DAO
	 */
	@Test
	public void PlayerStatusIsSavedAndDeleted() throws IOException {
		player.setUsername("signintest1");
		playerService.savePlayerStatus(player, true);

		opponent.setUsername("signintest");
		playerService.savePlayerStatus(opponent, true);

		assertNotNull("Player Status is saved", playerService.getPlayersQueue(player));

		playerService.deletePlayerStatus(player);
		playerService.deletePlayerStatus(opponent);
	}

	/**
	 * Player Request is deleted if exists via DAO
	 */
	@Test
	public void PlayerRequestIsSavedAndDeleted() throws IOException {
		player.setUsername("signintest");
		opponent.setUsername("signintest1");
		playerService.requestOpponent(player, opponent);

		assertNotNull("Player Request is saved", playerService.checkRequest(opponent));
		assertEquals(1, playerService.checkRequest(opponent).size());

		playerService.deletePlayerRequests(player);
		playerService.deletePlayerRequests(opponent);
	}


	/**
	 * Player Opponents records are saved and deleted
	 */
	@Test
	public void PlayerOpponentsIsSavedAndDeleted() throws IOException {
		player.setUsername("signintest");
		opponent.setUsername("signintest1");
		playerService.requestOpponent(player, opponent);
		playerService.registerOpponent(opponent, player);

		assertNotNull("Player opponent is saved", playerService.checkRequestAcceptance(player, opponent));
		assertEquals(true, playerService.checkRequestAcceptance(player, opponent));

		playerService.deletePlayerOpponentRecords(player);
		playerService.deletePlayerOpponentRecords(opponent);
	}

}