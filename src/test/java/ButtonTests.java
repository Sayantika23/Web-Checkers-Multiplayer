import com.webcheckers.model.Button;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * The Class ButtonTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class ButtonTests {
	
    private Button button;
    private static final String buttonType = "submit";
    private static final String buttonClass = "RED";
    private static final String buttonText = "Submit";

    
    @Before
    public void setup() {
    	this.button = new Button();
    }
 
    @After
    public void destroy() {
    	this.button = null;
    }

    /**
     * button Type should not be null.
     */
    @Test
    public void buttonTypeShouldNotBeNull() {
        this.button.setButtonType(buttonType);
        assertNotNull("Button Type must not be null", button.getButtonType());
    }

    /**
     * button Color Class should not be null.
     */
    @Test
    public void buttonClassShouldNotBeNull() {
        this.button.setButtonClass(buttonClass);
        assertNotNull("Button Color Class must not be null", button.getButtonClass());
    }

    /**
     * button Color should not be null.
     */
    @Test
    public void buttonTextShouldNotBeNull() {
        this.button.setButtonText(buttonText);
        assertNotNull("Button Text must not be null", button.getButtonText());
    }
}