import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.service.PlayerService;
import com.webcheckers.ui.PostSigninController;
import com.webcheckers.ui.PostSignupController;
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
 * The Class PostSigninControllerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class PostSigninControllerTests {

	/** The game. */
	private Game game;

	private PostSigninController CuT;

	/** The player service. */
	private PlayerService playerService;
	private PlayerDaoImpl playerDaoImpl;

	private Request request;
	private Session session;
	private Response response;
	private Human human;
	/**
	 * Instantiates a new home page tests.
	 */
	public PostSigninControllerTests() {
		try {
			this.game = new Game();
			this.CuT = new PostSigninController(game);
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
    public void PostSigninControllerShouldNotBeNull() {
    	assertNotNull("Home controller must not be null", CuT);
    }

	/**
	 * Test that the signin is handled properlyz
	 */
	@Test
	public void SigninHandler() {

		when(request.queryParams("inputUsername")).thenReturn("kishan");
		when(request.queryParams("inputPassword")).thenReturn("kishan");

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
		if("Incorrect Username/Password".equals(vm.get("message"))){
			assertEquals("home.ftl", result.getViewName());
		} else {
			assertEquals("playerselection.ftl", result.getViewName());
		}
	}
}