import com.webcheckers.model.Move;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The Class CheckerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class MoveTests {

    /** The game. */
    private Move move;
    public int currRow = 0, currCol=0, movRow=1, movCol=1;


    @Before
    public void setup() {
    	this.move = new Move(currRow, currCol, movRow, movCol);
    }

    @After
    public void destroy() {
    	this.move = null;
    }

    /**
     * Move Space should not be null.
     */
    @Test
    public void MoveSpaceShouldNotBeNull() {
        Pair<Integer, Integer> spaceMoved = this.move.getSpaceInbetween();
        assertNotNull("Space moved must not be null", spaceMoved.getValue());
    }

    /**
     * Move Jump should have one skip.
     */
    @Test
    public void SpaceMustBeSkippedForJump() {
        this.move = new Move(0, 0, 2, 2);
        Pair<Integer, Integer> spaceMoved = this.move.getSpaceInbetween();
        assertEquals(Long.valueOf(1),Long.valueOf(spaceMoved.getValue()));
    }
}