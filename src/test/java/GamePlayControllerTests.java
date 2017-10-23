import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.model.Game;

public class GamePlayControllerTests {

	/** The game. */
	private Game game;

	/**
	 * Initialize and create new Game instance.
	 */
	@Before
	public void setup() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Destroy game instance.
	 */
	@After
	public void destroy() {
		this.game = null;
	}

	/**
	 * Game play controller should not be null.
	 */
	@Test
	public void gamePlayControllerShouldNotBeNull() {
		GamePlayController gamePlayController = game.getGamePlayController();
		assertNotNull("GamePlayController must not be null", gamePlayController);
	}
}
