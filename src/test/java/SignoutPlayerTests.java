import static org.junit.Assert.assertNull;

import org.junit.Test;
import com.webcheckers.model.Player;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static spark.Spark.*;

/**
 * The Class SignoutPlayerTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class SignoutPlayerTests {

	/**
	 * Logged out player should be null.
	 */
	@Test
	public void loggedOutPlayerShouldBeNull() {
		
		post("/", new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				final Session session = request.session();
				session.attribute("player", null);
				return null;
			}
		});

		get("/", new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				Session session = request.session();
				final Player player = session.attribute("player");
				assertNull("Logged out player must be null", player);
				return null;
			}
		});
	}
}