import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.PlayerController;
import org.junit.Test;

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

	/** The board. */
	private Board board;

	/** The board iterator. */
	private ArrayList<Row> boardIterator;
	private ArrayList<Checker> squares;
	private ArrayList<Square> squareIterator;
	private ArrayList<String> redCheckers;
	private ArrayList<String> whiteCheckers;
	private String checkerColor;
	private Checker checker;
	private int count;

	@Before
	public void setup() {
		try {
			this.game = new Game();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.redCheckers = new ArrayList<String>();
		this.whiteCheckers = new ArrayList<String>();
		this.board = new Board();
		this.boardIterator = board.iterator();
		this.squares = new ArrayList<Checker>();
		this.squareIterator = new ArrayList<Square>();

		for (Row row : boardIterator) {
			squareIterator = row.iterator();
			for (Square square : squareIterator) {
				checker = square.getPiece();
				squares.add(checker);
				checkerColor = checker.getColorClass();
				if (square.isValid()) {
					if (checker.getType().equals("checker") && !checkerColor.equals("transparent")) {
						addToCheckersList(checkerColor);
					}
					count++;
				}
			}
		}
	}


	@After
	public void destroy() {
		this.board = null;
		this.redCheckers = null;
		this.whiteCheckers = null;
		this.board = null;
		this.boardIterator = null;
		this.squares = null;
		this.squareIterator = null;
		this.checkerColor = null;
		this.checker = null;
	}

	private void addToCheckersList(String checkerColor) {
		switch (checkerColor) {
		case "red":
			redCheckers.add(checkerColor);
			break;
		case "white":
			whiteCheckers.add(checkerColor);
			break;
		}
	}

	@Test
	public void initialCheckboardShouldHaveTwelveRedPieces() {
		assertEquals("Checkerboard should have twelve red checkers", 12, redCheckers.size());
	}

	@Test
	public void initialCheckboardShouldHaveTwelveWhitePieces() {
		assertEquals("Checkerboard should have twelve red checkers", 12, whiteCheckers.size());
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
	 * Game controller board should have sixty four squares.
	 */
	@Test
	public void gameControllerBoardShouldHaveSixtyFourSquares() {
		assertEquals("Board must have 64 squares", 64, squares.size());
	}

	/**
	 * Game id board should be null.
	 */
	@Test
	public void gameControllerBoardShouldHaveThirtyTwoValidSquares() {
		assertEquals("Board must have 32 valid squares", 32, count);
	}

	public void gameIdShouldNotBeNull() {
		assertNotNull("Game controller board must not be null", game.getGameId());
	}

	/**
	 * Player controller board should not be null.
	 */
	@Test
	public void playerControllerBoardShouldNotBeNull() {
		assertNotNull("Game controller board must not be null", game.getPlayerController());
	}

}