import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webcheckers.model.Game;
import com.webcheckers.ui.GameController;
import com.webcheckers.ui.HomeController;

/**
 * The Class GameControllerTest.
 * 
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class GameControllerTest {

	/** The request. */
	private Request request;

	/** The session. */
	private Session session;

	/** The response. */
	private Response response;

	/** The game. */
	private Game game;

	/** The CuT. */
	private GameController CuT;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		try {
			game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		CuT = new GameController(game);
		request = mock(Request.class);
		session = mock(Session.class);
		when(request.session()).thenReturn(session);
		when(session.attribute(eq("player"))).thenReturn(null);
		response = mock(Response.class);
	}
	
	/**
	 * Destroy.
	 */
	@After
	public void destroy() {
		game = null;
		CuT = null;
		request = null;
		session = null;
		response = null;
	}

	/**
	 * Unauthorized game page access due to 
	 * null player domain in session returns
	 * home page view with error message.
	 */
	@Test
	public void unauthorizedGamePageAccess() {
		when(request.attribute(eq("player"))).thenReturn(null);
		final ModelAndView result = CuT.handle(request, response);
		assertNotNull(result);
		final Object model = result.getModel();
		assertNotNull(model);
		@SuppressWarnings("unchecked")
		final Map<String, Object> vm = (Map<String, Object>) model;
		assertEquals(GameController.INVALID_ACCESS_MESSAGE, vm.get(HomeController.LOGIN_MESSAGE));
		assertEquals(HomeController.HOME_VIEW_NAME, result.getViewName());
	}
}
