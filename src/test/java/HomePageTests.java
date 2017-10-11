import static org.junit.Assert.assertNotNull;

import java.io.IOException;

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
	 * Instantiates a new home page tests.
	 */
	public HomePageTests() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
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