import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.webcheckers.model.Game;
import com.webcheckers.ui.HomeController;

public class HomePageTests {
	
	private Game game;
	
	public HomePageTests() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @Test
    public void homeControllerShouldNotBeNull() {
    	HomeController homeController = new HomeController(game);
    	assertNotNull("Home controller must not be null", homeController);
    } 
}