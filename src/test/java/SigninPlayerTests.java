import static org.junit.Assert.assertNotNull;

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
	 * Instantiates a new signin player tests.
	 */
	public SigninPlayerTests() {
		try {
			this.game = new Game();
			this.playerService = game.getPlayerController().getPlayerService();
		} catch (IOException e) {
			e.printStackTrace();
		}
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