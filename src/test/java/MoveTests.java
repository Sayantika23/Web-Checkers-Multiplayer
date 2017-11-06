import com.webcheckers.model.Move;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The Class MoveTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class MoveTests {

    /** The game. */
    private Move move;
    private Move move1;
    private Move move2;
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

    /**
     * Move object should be equal with same value for rows and columns
     */
    @Test
    public void MoveMustBeEqualForSameSourceAndTarget() {
        this.move = new Move(0, 0, 2, 2);
        this.move1 = new Move(0, 0, 2, 2);
        this.move2 = new Move(1, 1, 2, 2);
        assertEquals(this.move, this.move1);
        assertEquals(this.move, this.move);
        assertNotEquals(this.move, currCol);
        assertNotEquals(this.move, this.move2);
    }
}