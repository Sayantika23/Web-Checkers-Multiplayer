import com.webcheckers.model.Form;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The Class FormTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class FormTests {

    private Form form;

    @Before
    public void setup() {
        this.form = new Form();
    }

    @After
    public void destroy() {
        this.form = null;
    }
}