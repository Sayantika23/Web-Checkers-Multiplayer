import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import com.webcheckers.model.Game;
import com.webcheckers.service.PlayerService;

/**
 * The Class ServiceTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class ServiceTests {
	
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
     * Player service should not be null.
     */
    @Test
    public void playerServiceShouldNotBeNull() {
    	PlayerService playerService = game.getPlayerController().getPlayerService();
    	assertNotNull("PlayerService must not be null", playerService);
    }
}