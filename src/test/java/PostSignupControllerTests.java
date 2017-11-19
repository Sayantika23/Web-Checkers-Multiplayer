import com.webcheckers.model.Game;
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
 * The Class PostSignupControllerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class PostSignupControllerTests {

	private Game game;
	private PostSignupController CuT;
	private Request request;
	private Session session;
	private Response response;

	/**
	 * Instantiates a new home page tests.
	 */
	public PostSignupControllerTests() {
		try {
			this.game = new Game();
			this.CuT = new PostSignupController(game);
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
    public void PostSignupControllerShouldNotBeNull() {
    	assertNotNull("Home controller must not be null", CuT);
    }

	/**
	 * Test that the signup is handled properly if username does not exists
	 */
	@Test
	public void SignupWhenUsernameAvailable() {

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
		//   * test view name
		assertEquals("home.ftl", result.getViewName());
	}

	/**
	 * Test that the signup is handled properly if username exists
	 */
	@Test
	public void SignupWhenUsernameAlreadyExists() {

		when(request.queryParams("inputUsername")).thenReturn("signintest");
		when(request.queryParams("inputPassword")).thenReturn("signintest");
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