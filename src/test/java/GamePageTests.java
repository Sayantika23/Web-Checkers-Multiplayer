import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Checker;
import com.webcheckers.model.Game;
import com.webcheckers.model.Row;
import com.webcheckers.model.Square;
import com.webcheckers.ui.GameController;

/**
 * The Class GamePageTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class GamePageTests {

	/** The game. */
	private Game game;
	
	/** The game controller. */
	private GameController gameController;
	
	/** The game play controller. */
	private GamePlayController gamePlayController;
	
	/** The board. */
	private Board board;
	
	/** The board iterator. */
	private ArrayList<Row> boardIterator;
	
	/** The square iterator. */
	private ArrayList<Square> squareIterator;
	
	/** The squares. */
	private ArrayList<Checker> squares;
	
	/** The count  */
	private int count = 0;

	/**
	 * Initialize and create test variables.
	 */
	@Before
	public void setup() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.gameController = new GameController(game);
		this.gamePlayController = game.getGamePlayController();
		this.board = gamePlayController.getBoard();
		this.boardIterator = board.iterator();
		this.squares = new ArrayList<Checker>();
		this.squareIterator = new ArrayList<Square>();

		for (Row row : boardIterator) {
			squareIterator = row.iterator();
			for (Square square : squareIterator) {	
				squares.add(square.getPiece());
				if (square.isValid()) {
					count++;
				}
			}
		}
	}

	/**
	 * Destroy test variables.
	 */
	@After
	public void destroy() {
		this.game = null;
		this.gameController = null;
		this.gamePlayController = null;
		this.board = null;
		this.boardIterator = null;
		this.squares = null;
		this.squareIterator = null;
	}

	/**
	 * Home controller should not be null.
	 */
	@Test
	public void homeControllerShouldNotBeNull() {
		assertNotNull("Game controller must not be null", gameController);
	}

	/**
	 * Game controller board should not be null.
	 */
	@Test
	public void gameControllerBoardShouldNotBeNull() {
		assertNotNull("Game controller board must not be null", board);
	}

	/**
	 * Game controller board should have eight rows.
	 */
	@Test
	public void gameControllerBoardShouldHaveEightRows() {
		assertEquals("Board must have 8 rows", 8, boardIterator.size());
	}

	/**
	 * Game controlle board should have sixty four squares.
	 */
	@Test
	public void gameControlleBoardShouldHaveSixtyFourSquares() {
		assertEquals("Board must have 64 squares", 64, squares.size());
	}
	
	/**
	 * Game controller board should have thirty two valid squares.
	 */
	@Test
	public void gameControllerBoardShouldHaveThirtyTwoValidSquares() {
		assertEquals("Board must have 64 squares", 32, count);
	}
}