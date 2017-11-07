import static org.junit.Assert.assertNotNull;

import com.webcheckers.controller.GuiController;
import com.webcheckers.model.Button;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * The Class CheckerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class GuiControllerTests {


    private GuiController guiController;
    private Button button;
    
    @Before
    public void setup() {
    	this.guiController = new GuiController();
    }

    @After
    public void destroy() {
    	this.guiController = null;
    }

    /**
     * Home Signup button should be created
     */
    @Test
    public void HomeSignupButtonShouldBeCreated() {
        button = guiController.getHomeSignupButton();
        assertNotNull("Home Signup button must not be null", button);
    }

    /**
     * Home Signin button should be created
     */
    @Test
    public void HomeSigninButtonShouldBeCreated() {
        button = guiController.getHomeSigninButton();
        assertNotNull("Home Signin button must not be null", button);
    }

    /**
     * Home Signout button should be created
     */
    @Test
    public void HomeSignoutButtonShouldBeCreated() {
        button = guiController.getGameSignoutButton();
        assertNotNull("Home Signout button must not be null", button);
    }

    /**
     * Pause button should be created
     */
    @Test
    public void PauseButtonShouldBeCreated() {
        button = guiController.getPauseButton();
        assertNotNull("Pause button must not be null", button);
    }

    /**
     * Select button should be created
     */
    @Test
    public void SelectButtonShouldBeCreated() {
        button = guiController.getSelectButton();
        assertNotNull("Select button must not be null", button);
    }

    /**
     * Computer Player Button should be created
     */
    @Test
    public void ComputerPlayerButtonShouldBeCreated() {
        button = guiController.getComputerPlayerSelectionButton();
        assertNotNull("Computer Player button must not be null", button);
    }

    /**
     * Human Player Button should be created
     */
    @Test
    public void HumanPlayerButtonShouldBeCreated() {
        button = guiController.getHumanPlayerSelectionButton();
        assertNotNull("Human Player button must not be null", button);
    }
}
