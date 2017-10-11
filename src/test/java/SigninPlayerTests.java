import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import java.io.IOException;

import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;

public class SigninPlayerTests {

	private Game game;
	private PlayerService playerService;

	public SigninPlayerTests() {
		try {
			this.game = new Game();
			this.playerService = game.getPlayerController().getPlayerService();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void registeredPlayerLoginShouldAuthenticate() {

		Player testPlayer = null;
		final Human human = new Human();
		human.setUsername("signintest");
		human.setPassword("password");
		Player existingPlayer = playerService.findPlayer(human);

		if (existingPlayer == null) {
			playerService.savePlayer(human);
			testPlayer = playerService.findPlayer(human);
		} else {
			testPlayer = existingPlayer;
		}
		final boolean authenticated = playerService.authenticate(testPlayer);
		assertNotNull("Authenticated player must not be null", authenticated);
	}
}