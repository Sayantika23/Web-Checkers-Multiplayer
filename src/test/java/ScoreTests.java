import com.webcheckers.model.Score;
import org.junit.After;
import org.junit.Before;

/**
 * The Class ScoreTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class ScoreTests {

    @SuppressWarnings("unused")
	private Score score;

    @Before
    public void setup() {
        this.score = new Score();
    }

    @After
    public void destroy() {
        this.score = null;
    }
}