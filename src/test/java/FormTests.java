import com.webcheckers.model.Form;
import org.junit.After;
import org.junit.Before;

// TODO: Auto-generated Javadoc
/**
 * The Class FormTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class FormTests {
	
    @SuppressWarnings("unused")
	private Form form;

    /**
     * Setup.
     */
    @Before
    public void setup() {
        this.form = new Form();
    }

    /**
     * Destroy.
     */
    @After
    public void destroy() {
        this.form = null;
    }
}