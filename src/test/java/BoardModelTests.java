import com.google.gson.Gson;
import com.webcheckers.model.Board;
import com.webcheckers.model.BoardModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * The Class BoardModelTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class BoardModelTests {

    /** The game. */
    private BoardModel boardModel;
    private Board board;

    @Before
    public void setup() {
        this.board = new Board();
    	this.boardModel = new BoardModel();
    }

    @After
    public void destroy() {
    	this.boardModel = null;
    }

    /**
     * BoardModel is set properly
     */
    @Test
    public void ValidBoardIsSet() {
        Gson gson = new Gson();
        String boardJson = gson.toJson(board);
        Array boardArray = boardModel.setBoardModel(boardJson);
        assertNotNull("BoardModel must not be null", boardArray);
    }

    /**
     * BoardModel board should be numm
     */
    @Test
    public void BoardInBoardModelShouldBeNull() {
        assertNull("BoardModel must not be null", boardModel.getBoardModel());
    }
}