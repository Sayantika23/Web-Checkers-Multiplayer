import com.webcheckers.model.Game;
import com.webcheckers.model.Human;
import com.webcheckers.ui.PostSignoutController;
import org.junit.Before;
import org.junit.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * The Class SignupControllerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class PostSignoutControllerTests {
	
	private Game game;
	private PostSignoutController CuT;
	private Request request;
	private Session session;
	private Response response;

	/**
	 * Instantiates a new home page tests.
	 */
	public PostSignoutControllerTests() {
		try {
			this.game = new Game();
			this.CuT = new PostSignoutController(game);
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
		when(session.attribute(eq("player"))).thenReturn(new Human());
		response = mock(Response.class);
	}
    
    /**
     * Signup controller should not be null.
     */
    @Test
    public void SignupControllerShouldNotBeNull() {
    	assertNotNull("Home controller must not be null", CuT);
    }

	/**
	 * Test that the signout is handled and home page is generated
	 */
	@Test
	public void signout_is_handled() {

		// Invoke the test
		final ModelAndView result = CuT.handle(request, response);

		// Analyze the results:
		//   * result is non-null
		assertNotNull(result);
		//   * model is a non-null Map
		final Object model = result.getModel();
		assertNotNull(model);
		assertTrue(model instanceof Map);

		@SuppressWarnings("unchecked")
		final Map<String, Object> vm = (Map<String, Object>) model;
		assertEquals("Web Checkers", vm.get("title"));
		assertEquals(false, vm.get("loginFail"));
		assertEquals(false, vm.get("signupFail"));
		assertEquals(true, vm.get("signinPage"));
		assertEquals(false, vm.get("newUserSignup"));
		//   * test view name
		assertEquals("home.ftl", result.getViewName());
	}
}