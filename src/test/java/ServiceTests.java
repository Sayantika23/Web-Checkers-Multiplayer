import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import java.io.IOException;

import com.webcheckers.model.Game;
import com.webcheckers.service.PlayerService;

/**
 * The Class ServiceTests.
 */
public class ServiceTests {
	
	/** The game. */
	private Game game;
	
	/**
	 * Instantiates a new service tests.
	 */
	public ServiceTests() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Player service should not be null.
     */
    @Test
    public void playerServiceShouldNotBeNull() {
    	PlayerService playerService = game.getPlayerController().getPlayerService();
    	assertNotNull("PlayerService must not be null", playerService);
    }
}