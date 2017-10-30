
import com.webcheckers.model.Button;
import org.junit.Test;

import static org.junit.Assert.*;

    //@author>Ebtesam a <
public class ButtonTest1 {
    @Test
    public void ButtonClassShouldBeAssigned() throws Exception {
        final Button button=new Button();
        button.setButtonClass("btn btn-warning margin-10");
        assertEquals("btn btn-warning margin-10", button.getButtonClass());
    }

    @Test
    public void ButtonClassShouldBeAssign() throws Exception {
        final Button button=new Button();
        button.setButtonType("submit");
        assertEquals("submit", button.getButtonType());
    }

    @Test
    public void getButtonType() throws Exception {
        final Button signup=new Button();
        assertEquals("submit", signup.getButtonType());
    }

    @Test
    public void ButtonTextShouldBeAssigned() throws Exception {
        final Button button=new Button();
        button.setButtonText("submit");
        assertEquals("submit", button.getButtonText());
    }

}