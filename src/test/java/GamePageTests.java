import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Row;
import com.webcheckers.ui.GameController;

public class GamePageTests {
	
	private Game game;
	private GameController gameController;
	private GamePlayController gamePlayController;
	private Board board;
	
	public GamePageTests() {
		try {
			this.game = new Game();
	    	this.gameController = new GameController(game);
			this.gamePlayController = game.getGamePlayController();
	    	this.board = gamePlayController.getBoard();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @Test
    public void homeControllerShouldNotBeNull() {
    	assertNotNull("Game controller must not be null", gameController);
    }
    
    @Test
    public void gameControllerBoardShouldNotBeNull() {
    	assertNotNull("Game controller board must not be null", board);
    }
    
    @Test
    public void gameControllerBoardShouldHaveEightRows() {
    	ArrayList<Row> iterator = board.iterator();
    	assertNotNull("Game controller board must not be null", board);
    	assertEquals("Board must have eight rows", 8, iterator.size());
    }
}