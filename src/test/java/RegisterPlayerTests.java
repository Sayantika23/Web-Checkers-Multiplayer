import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import java.io.IOException;

import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;

public class RegisterPlayerTests {

	private Game game;
	private PlayerService playerService;

	public RegisterPlayerTests() {
		try {
			this.game = new Game();
			this.playerService = game.getPlayerController().getPlayerService();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void registeredPlayerShouldNotBeNullAndAttributesShouldMatch() {
		Player testPlayer;
		Human human = new Human();
		human.setUsername("test");
		human.setPassword("password");
		
		Player existingPlayer = playerService.findPlayer(human);

		if (existingPlayer == null) {
			playerService.savePlayer(human);
			testPlayer = playerService.findPlayer(human);
		} else {
			testPlayer = existingPlayer;
		}
		
		assertNotNull("Registered player must not be null", testPlayer);
		assertNotNull("Registered player username must not be null", testPlayer.getUsername());
		assertNotNull("Registered player password must not be null", testPlayer.getPassword());
		assertEquals("Registered player username must match test username", testPlayer.getUsername(), human.getUsername());
		assertEquals("Registered player password must match test password", testPlayer.getPassword(), human.getPassword());
	}
}