import static org.junit.Assert.assertEquals;
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
		Player testPlayer;
		Human player = new Human();
		player.setUsername("logintest");
		player.setPassword("password");
		
		Player existingPlayer = playerService.findPlayer(player);

		if (existingPlayer == null) {
			playerService.savePlayer(player);
			testPlayer = playerService.findPlayer(player);
		} else {
			testPlayer = existingPlayer;
		}
		
		final boolean loginStatus = playerService.authenticate(testPlayer);
		
		assertNotNull("Authentication login status must not be null", loginStatus);
	}
}