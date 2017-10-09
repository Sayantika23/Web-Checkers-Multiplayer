import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import java.io.IOException;

import com.webcheckers.model.Game;
import com.webcheckers.service.PlayerService;

public class ServiceTests {
	
	private Game game;
	
	public ServiceTests() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @Test
    public void playerServiceShouldNotBeNull() {
    	PlayerService playerService = game.getPlayerController().getPlayerService();
    	assertNotNull("PlayerService must not be null", playerService);
    }
}