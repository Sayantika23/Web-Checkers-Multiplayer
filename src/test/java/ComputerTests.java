import com.webcheckers.model.Computer;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The Class ComputerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class ComputerTests {

    /** The game. */
    private Computer computer;
    private String username = "computer";
    private String password = "computer";

    @Before
    public void setup() {
    	this.computer = new Computer();
    }

    @After
    public void destroy() {
    	this.computer = null;
    }

    /**
     * Username is set and get properly
     */
    @Test
    public void ValidUsernameIsSetAndReturned() {
        computer.setUsername(username);
        assertNotNull("Username must not be null", computer.getUsername());
        assertEquals(this.username, computer.getUsername());
    }

    /**
     * Username is set and get properly
     */
    @Test
    public void ValidPAsswordIsSetAndReturned() {
        computer.setPassword(password);
        assertNotNull("Username must not be null", computer.getPassword());
        assertEquals(this.password, computer.getPassword());
    }

}