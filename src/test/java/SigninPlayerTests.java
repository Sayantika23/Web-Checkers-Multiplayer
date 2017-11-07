import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;

/**
 * The Class SigninPlayerTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class SigninPlayerTests {

	/** The game. */
	private Game game;
	
	/** The player service. */
	private PlayerService playerService;
	
	/**
	 * Initialize and create test variables.
	 */
	@Before
	public void setup() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.playerService = game.getPlayerController().getPlayerService();
	}

	/**
	 * Destroy test variables.
	 */
	@After
	public void destroy() {
		this.game = null;
		this.playerService = null;
	}

	/**
	 * Registered player login should authenticate.
	 */
	@Test
	public void registeredPlayerLoginShouldAuthenticate() {

		Player testPlayer = null;
		Player human = new Human();
		human.setUsername("testusername");
		human.setPassword("password");
		Player existingPlayer = playerService.findPlayer(human);

		if (existingPlayer == null) {
			playerService.savePlayer(human);
			testPlayer = playerService.findPlayer(human);
		} else {
			testPlayer = existingPlayer;
		}

		System.out.println("TESTPLAYER: " + testPlayer);
		final boolean authenticated = playerService.authenticate(testPlayer);
		assertNotNull("Authenticated player must not be null", authenticated);
	}
}