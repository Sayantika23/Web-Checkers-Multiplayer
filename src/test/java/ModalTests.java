import com.webcheckers.model.Modal;
import org.junit.After;
import org.junit.Before;

/**
 * The Class ModalTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class ModalTests {

    private Modal modal;

    @Before
    public void setup() {
        this.modal = new Modal();
    }

    @After
    public void destroy() {
        this.modal = null;
    }
}