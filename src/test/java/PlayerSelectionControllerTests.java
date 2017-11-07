import com.webcheckers.model.Game;
import com.webcheckers.ui.PlayerSelectionController;
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
 * The Class PlayerSelectionControllerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class PlayerSelectionControllerTests {

	/** The game. */
	private Game game;

	private PlayerSelectionController CuT;

	private Request request;
	private Session session;
	private Response response;

	/**
	 * Instantiates a new home page tests.
	 */
	public PlayerSelectionControllerTests() {
		try {
			this.game = new Game();
			this.CuT = new PlayerSelectionController(game);
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
	}
    
    /**
     * Signup controller should not be null.
     */
    @Test
    public void PlayerSelectionControllerShouldNotBeNull() {
    	assertNotNull("Home controller must not be null", CuT);
    }

	/**
	 * Test that the player selection view will generate a player selection page
	 */
	@Test
	public void new_player_selection_page() {

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
		assertNull("Computer levels should be null", vm.get("levels"));
		assertEquals("Web Checkers", vm.get("title"));
		assertEquals(true, vm.get("is_human"));
		//   * test view name
		assertEquals("playerlist.ftl", result.getViewName());
	}

	/**
	 * Test that the player selection view will generate a player selection page for computer
	 */
	@Test
	public void new_player_selection_page_for_computer() {

		when(request.queryParams("player")).thenReturn("computer");
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
		assertNotNull("Computer levels should not be null", vm.get("levels"));
		assertEquals("Web Checkers", vm.get("title"));
		assertEquals(false, vm.get("is_human"));
		//   * test view name
		assertEquals("playerlist.ftl", result.getViewName());
	}


}