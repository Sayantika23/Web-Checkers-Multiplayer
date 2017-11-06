import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import com.webcheckers.model.Game;
import com.webcheckers.ui.HomeController;

/**
 * The Class HomePageTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class HomePageTests {
	
	/** The game. */
	private Game game;

	private HomeController CuT;

	private Request request;
	private Session session;
	private Response response;

	/**
	 * Instantiates a new home page tests.
	 */
	public HomePageTests() {
		try {
			this.game = new Game();
			this.CuT = new HomeController(game);
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
     * Home controller should not be null.
     */
    @Test
    public void homeControllerShouldNotBeNull() {
    	assertNotNull("Home controller must not be null", CuT);
    }

	/**
	 * Test that the Home view will generate a home page
	 */
	@Test
	public void new_signup_page() {
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