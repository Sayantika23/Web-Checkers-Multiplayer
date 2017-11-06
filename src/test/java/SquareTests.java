import com.webcheckers.model.Checker;
import com.webcheckers.model.Square;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The Class SquareTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class SquareTests {

    private Square square;
    private Checker checker;
    private int cellId = 1;
    @Before
    public void setup() {
    	this.checker = new Checker();
        this.square = new Square(cellId, checker, true);
    }

    @After
    public void destroy() {
        this.checker = null;
    	this.square = null;
    }

    /**
     * Returns Valid cellID
     */
    @Test
    public void ValidCellIdIsReturned() {
        assertEquals(this.cellId, this.square.getCellId());
    }

    /**
     * Returns Valid cellID
     */
    @Test
    public void ValidSquareIsReturned() {
        assertEquals(true, this.square.isValid());
    }
}