import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.PlayerController;
import com.webcheckers.model.Game;

// TODO: Auto-generated Javadoc
/**
 * The Class ControllerTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class ControllerTests {

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

	/**
	 * Gui controller should not be null.
	 */
	@Test
	public void guiControllerShouldNotBeNull() {
		GuiController guiController = game.getGUIController();
		assertNotNull("GuiController must not be null", guiController);
	}

	/**
	 * Player controller should not be null.
	 */
	@Test
	public void playerControllerShouldNotBeNull() {
		PlayerController playerController = game.getPlayerController();
		assertNotNull("PlayerController must not be null", playerController);
	}
}