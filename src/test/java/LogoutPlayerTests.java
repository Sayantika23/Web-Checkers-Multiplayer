import static org.junit.Assert.assertNull;
import org.junit.Test;

import java.io.IOException;

import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;
import com.webcheckers.ui.PostSigninController;
import com.webcheckers.ui.PostSignoutController;

public class LogoutPlayerTests {

	private Game game;
	private PlayerService playerService;

	public LogoutPlayerTests() {
		try {
			this.game = new Game();
			this.playerService = game.getPlayerController().getPlayerService();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void loggedOutPlayerShouldBeNull() {
		
		Player loginPlayer;
		Player loggedOutPlayer = null;
		Human player = new Human();
		player.setUsername("signouttest");
		player.setPassword("password");
		
		Player existingPlayer = playerService.findPlayer(player);

		if (existingPlayer == null) {
			playerService.savePlayer(player);
			loginPlayer = playerService.findPlayer(player);
		} else {
			loginPlayer = existingPlayer;
		}
		
		final boolean loginStatus = playerService.authenticate(loginPlayer);
		
		if (loginStatus) {
			new PostSigninController(game);
			if (game.getPlayer() != null) {
				new PostSignoutController(game);
			}
		} else {
			loggedOutPlayer = loginPlayer;
		}
		assertNull("Logged out player must be null", loggedOutPlayer);
	}
}