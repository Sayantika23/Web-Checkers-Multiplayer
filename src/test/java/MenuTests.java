import com.webcheckers.model.Menu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The Class MenuTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class MenuTests {

    private Menu menu;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    @Before
    public void setup() {
        this.menu = new Menu();
    }

    @After
    public void destroy() {
        this.menu = null;
    }

    /**
     * Valid Player One Score is set and returned
     */
    @Test
    public void ValidPlayerOneScoreIsSetAndReturned() {
        menu.setPlayerOneScore(playerOneScore);
        assertNotNull("Player One Score must not be null", menu.getPlayerOneScore());
        assertEquals(this.playerOneScore, menu.getPlayerOneScore());
    }

    /**
     * Valid Player Two Score is set and returned
     */
    @Test
    public void ValidPlayerTwoScoreIsSetAndReturned() {
        menu.setPlayerTwoScore(playerTwoScore);
        assertNotNull("Player Two Score must not be null", menu.getPlayerTwoScore());
        assertEquals(this.playerTwoScore, menu.getPlayerTwoScore());
    }
}