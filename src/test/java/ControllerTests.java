import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import java.io.IOException;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.PlayerController;
import com.webcheckers.model.Game;

/**
 * The Class ControllerTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class ControllerTests {
	
	/** The game. */
	private Game game;
	
	/**
	 * Instantiates a new controller tests.
	 */
	public ControllerTests() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
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