//import com.webcheckers.controller.PlayerController;
//import com.webcheckers.controller.GuiController;
//import com.webcheckers.model.Game;
//import com.webcheckers.model.Human;
//import com.webcheckers.model.Player;
//import com.webcheckers.service.PlayerService;
//
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.assertEquals;
//import java.io.IOException;
//import java.util.List;
//
//import static org.junit.Assert.assertNotNull;
//
///**
// * The Class PlayerSelectionTests.
// *
// * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
// */
//public class PlayerSelectionTests {
//
//
//	/** The game. */
//	private Game game;
//
//	private Player human1;
//	private Player human2;
//	
//	
//	@Before
//	public void setup() {
//		try {
//			this.game = new Game();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//    /**
//     * Gui controller should not be null.
//     */
//    @Test
//    public void guiControllerShouldNotBeNull() {
//    	GuiController guiController = game.getGUIController();
//    	assertNotNull("GuiController must not be null", guiController);
//    }
//    
//    /**
//     * Player controller should not be null.
//     */
//    @Test
//    public void playerControllerShouldNotBeNull() {
//    	PlayerController playerController = game.getPlayerController();
//    	assertNotNull("PlayerController must not be null", playerController);
//    }
//
//	/**
//	 * Player Service should not be null.
//	 */
//	@Test
//	public void playerServiceShouldNotBeNull() {
//		PlayerController playerController = game.getPlayerController();
//		PlayerService playerService = playerController.getPlayerService();
//		assertNotNull("Player Service must not be null", playerService);
//	}
//
//	/**
//	 * Player Queue should be null if no player has logged in.
//	 */
//	@Test
//	public void playerQueueShouldBeNullAtStart() {
//		PlayerController playerController = game.getPlayerController();
//		PlayerService playerService = playerController.getPlayerService();
//		human1 = new Human();
//		human1.setUsername("signintest");
//		playerService.savePlayerStatus(human1, true);
//		List<String> players = playerService.getPlayersQueue(human1);
//		assertEquals(0,players.size());
//	}
//
//	/**
//	 * Player Queue should not be null if player has logged in.
//	 */
////	@Test
////	public void playerQueueShouldNotBeNullWhenAvailable() {
////		PlayerController playerController = game.getPlayerController();
////		PlayerService playerService = playerController.getPlayerService();
////
////		human1 = new Human();
////		human1.setUsername("signintest");
////		playerService.savePlayerStatus(human1, true);
////
////		human2 = new Human();
////		human2.setUsername("signintest1");
////		playerService.savePlayerStatus(human2, true);
////
////		List<String> players = playerService.getPlayersQueue(human2);
////
////		playerService.deletePlayerStatus(human2);
////		playerService.deletePlayerStatus(human1);
////		assertEquals(1, players.size());
////	}
//
//	/**
//	 * Player Requests should be null if no player has send request.
//	 */
//	@Test
//	public void playerRequestsShouldBeNullAtStart() {
//		PlayerController playerController = game.getPlayerController();
//		PlayerService playerService = playerController.getPlayerService();
//
//		human1 = new Human();
//		human1.setUsername("signintest");
//		playerService.savePlayerStatus(human1, true);
//
//		List<String> requests = playerService.checkRequest(human1);
//
//		assertEquals(0, requests.size());
//	}
//
//	/**
//	 * Player Requests should not be null if a player has send request.
//	 */
//	@Test
//	public void playerRequestsShouldNotBeNullWhenAvailable() {
//		PlayerController playerController = game.getPlayerController();
//		PlayerService playerService = playerController.getPlayerService();
//
//		human1 = new Human();
//		human1.setUsername("signintest");
//		playerService.savePlayer(human1);
//
//		human2 = new Human();
//		human2.setUsername("signintest1");
//		playerService.savePlayer(human2);
//
//		playerService.requestOpponent(human1, human2);
//
//		List<String> invites = playerService.checkRequest(human2);
//		assertEquals(1, invites.size());
//	}
//}