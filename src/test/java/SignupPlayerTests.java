import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.io.IOException;

import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;

/**
 * The Class SignupPlayerTests.
 */
public class SignupPlayerTests {

	/** The game. */
	private Game game;
	
	/** The player service. */
	private PlayerService playerService;

	/**
	 * Instantiates a new signup player tests.
	 */
	public SignupPlayerTests() {
		try {
			this.game = new Game();
			this.playerService = game.getPlayerController().getPlayerService();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Registered player should not be null and attributes should match.
	 */
	@Test
	public void registeredPlayerShouldNotBeNullAndAttributesShouldMatch() {
		
		Player testPlayer;
		Human player = new Human();
		player.setUsername("signuptest");
		player.setPassword("password");

		Player existingPlayer = playerService.findPlayer(player);

		if (existingPlayer == null) {
			playerService.savePlayer(player);
			testPlayer = playerService.findPlayer(player);
		} else {
			testPlayer = existingPlayer;
		}
		
		assertNotNull("Registered player must not be null", testPlayer);
		assertNotNull("Registered player username must not be null", testPlayer.getUsername());
		assertNotNull("Registered player password must not be null", testPlayer.getPassword());
		assertEquals("Registered player username must match test username", testPlayer.getUsername(),
				player.getUsername());
		assertEquals("Registered player password must match test password", testPlayer.getPassword(),
				player.getPassword());
	}
}