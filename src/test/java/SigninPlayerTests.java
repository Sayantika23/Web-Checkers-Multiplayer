import static org.junit.Assert.assertNotNull;
import static spark.Spark.post;

import org.junit.Test;

import java.io.IOException;

import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

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
		final Human human = new Human();
		human.setUsername("signintest");
		human.setPassword("password");
		
		post("/game", new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {

				final boolean authenticated = playerService.authenticate(human);

				Session session = request.session();
				if (authenticated) {
					session.attribute("player", human);
				}
				
				final Player sessionPlayer = session.attribute("player");
				assertNotNull("Logged out player must not be null", sessionPlayer);
				return null;
			}
		});
		
	}
}