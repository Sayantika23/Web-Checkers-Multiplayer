import static org.junit.Assert.assertNotNull;

import com.webcheckers.model.Checker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * The Class CheckerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class CheckerTests {

    /** The game. */
    private Checker checker;
    private static final String checkerType = "checker";
    private static final String checkerColor = "RED";
    private static final String checkerColorClass = "red";

    
    @Before
    public void setup() {
    	this.checker = new Checker();
    }
 
    @After
    public void destroy() {
    	this.checker = null;
    }

    /**
     * Checker Type should not be null.
     */
    @Test
    public void checkerTypeShouldNotBeNull() {
        this.checker.setType(checkerType);
        assertNotNull("Checker Type must not be null", checker.getType());
    }

    /**
     * Checker Color Class should not be null.
     */
    @Test
    public void checkerColorClassShouldNotBeNull() {
        this.checker.setColorClass(checkerColorClass);
        assertNotNull("Checker Color Class must not be null", checker.getColorClass());
    }

    /**
     * Checker Color should not be null.
     */
    @Test
    public void checkerColorShouldNotBeNull() {
        this.checker.setDataColor(checkerColor);
        assertNotNull("Checker Color must not be null", checker.getDataColor());
    }
}