import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webcheckers.model.Game;
import com.webcheckers.ui.HomeController;

/**
 * The Class HomePageTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class HomePageTests {
	
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
     * Home controller should not be null.
     */
    @Test
    public void homeControllerShouldNotBeNull() {
    	HomeController homeController = new HomeController(game);
    	assertNotNull("Home controller must not be null", homeController);
    } 
}