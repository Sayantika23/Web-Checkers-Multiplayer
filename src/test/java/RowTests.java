import com.webcheckers.model.Row;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The Class CheckerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class RowTests {

    /** The row. */
    private Row row;
    private int rowNumber = 0;
    @Before
    public void setup() {
    	this.row = new Row(rowNumber);
    }

    @After
    public void destroy() {
    	this.row = null;
    }

    /**
     * Row Number should not be Null when initialized
     */
    @Test
    public void RowNumberShouldNotBeNull() {
        int rowNum = row.getRowNumber();
        assertNotNull("Row Number must not be null for a row", rowNum);
    }
}