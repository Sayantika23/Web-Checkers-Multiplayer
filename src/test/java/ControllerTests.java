import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import java.io.IOException;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.PlayerController;
import com.webcheckers.model.Game;

public class ControllerTests {
	
	private Game game;
	
	public ControllerTests() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @Test
    public void gamePlayControllerShouldNotBeNull() {
    	GamePlayController gamePlayController = game.getGamePlayController();
    	assertNotNull("GamePlayController must not be null", gamePlayController);
    }
    
    @Test
    public void guiControllerShouldNotBeNull() {
    	GuiController guiController = game.getGUIController();
    	assertNotNull("GuiController must not be null", guiController);
    }
    
    @Test
    public void playerControllerShouldNotBeNull() {
    	PlayerController playerController = game.getPlayerController();
    	assertNotNull("PlayerController must not be null", playerController);
    }
}