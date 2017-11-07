import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.service.PlayerService;
import com.webcheckers.ui.GameController;
import org.junit.Before;
import org.junit.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The Class GameControllerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class GameControllerTests {

	/** The game. */
	private Game game;

	private GameController CuT;

	private Request request;
	private Session session;
	private Response response;
	private Human human;
	private PlayerService playerService;
	private PlayerDaoImpl playerDaoImpl;
	/**
	 * Instantiates a new home page tests.
	 */
	public GameControllerTests() {
		try {
			this.game = new Game();
			this.CuT = new GameController(game);
			human = new Human();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Setup new mock objects for each test.
	 */
	@Before
	public void setup() {
		request = mock(Request.class);
		session = mock(Session.class);
		when(request.session()).thenReturn(session);
		response = mock(Response.class);
		playerService = mock(PlayerService.class);
		playerDaoImpl = mock(PlayerDaoImpl.class);
	}
    
    /**
     * Signup controller should not be null.
     */
    @Test
    public void GameControllerShouldNotBeNull() {
    	assertNotNull("Home controller must not be null", CuT);
    }

	/**
	 * Test that the selected player is human and reponse type is acceptance
	 */
	@Test
	public void human_player_game_page() {

		when(request.queryParams("opponentName")).thenReturn("signintest");
		when(request.queryParams("opponentType")).thenReturn("human");
		when(request.queryParams("requestType")).thenReturn("invite");

		human.setUsername("signintest");
		when(session.attribute("player")).thenReturn(human);

		when(playerDaoImpl.findPlayerByUsername(human.getUsername())).thenReturn(human);
		when(playerService.findPlayer(human)).thenReturn(human);
		// Invoke the test
		final ModelAndView result = CuT.handle(request, response);

		// Analyze the results:
		//   * result is non-null
		assertNotNull(result);
		//   * model is a non-null Map
		final Object model = result.getModel();
		assertNotNull(model);
		assertTrue(model instanceof Map);
		//   * model contains all necessary View-Model data
		@SuppressWarnings("unchecked")
		final Map<String, Object> vm = (Map<String, Object>) model;
		assertEquals("Game Page", vm.get("title"));
		//   * test view name
		assertEquals("game.ftl", result.getViewName());
	}

	/**
	 * Test that the selected player is human and response type is request
	 */
	@Test
	public void RequestIsMadeForGame() {

		when(request.queryParams("opponentName")).thenReturn("signintest");
		when(request.queryParams("opponentType")).thenReturn("human");
		when(request.queryParams("requestType")).thenReturn("request");

		human.setUsername("signintest");
		when(session.attribute("player")).thenReturn(human);

		when(playerDaoImpl.findPlayerByUsername(human.getUsername())).thenReturn(human);
		when(playerService.findPlayer(human)).thenReturn(human);
		// Invoke the test
		final ModelAndView result = CuT.handle(request, response);

		// Analyze the results:
		//   * result is non-null
		assertNotNull(result);
		//   * model is a non-null Map
		final Object model = result.getModel();
		assertNotNull(model);
		assertTrue(model instanceof Map);
		//   * model contains all necessary View-Model data
		@SuppressWarnings("unchecked")
		final Map<String, Object> vm = (Map<String, Object>) model;
		assertEquals("Game Page", vm.get("title"));
		//   * test view name
		assertEquals("game.ftl", result.getViewName());
	}

	/**
	 * Test that the Game will exit if no player
	 */
	@Test
	public void NoPlayerInSession() {

		when(request.queryParams("opponentName")).thenReturn("");
		when(request.queryParams("opponentType")).thenReturn("");
		when(request.queryParams("requestType")).thenReturn("");

		human.setUsername("signintest");
		when(session.attribute("player")).thenReturn(null);
		// Invoke the test
		final ModelAndView result = CuT.handle(request, response);

		// Analyze the results:
		//   * result is non-null
		assertNotNull(result);
		//   * model is a non-null Map
		final Object model = result.getModel();
		assertNotNull(model);
		assertTrue(model instanceof Map);
		//   * model contains all necessary View-Model data
		@SuppressWarnings("unchecked")
		final Map<String, Object> vm = (Map<String, Object>) model;
		assertEquals("Web Checkers", vm.get("title"));
		//   * test view name
		assertEquals("home.ftl", result.getViewName());
	}


}