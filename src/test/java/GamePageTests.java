import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.PlayerController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Checker;
import com.webcheckers.model.Game;
import com.webcheckers.model.Row;
import com.webcheckers.model.Square;

/**
 * The Class GamePageTests.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class GamePageTests {

	/** The game. */
	private Game game;
	
	/** The game play controller. */
	private GamePlayController gamePlayController;

	private PlayerController playerController;

	/** The board. */
	private Board board;

	/** The board iterator. */
	private ArrayList<Row> boardIterator;
	private ArrayList<Checker> squares;
	private ArrayList<Square> squareIterator;
	private ArrayList<String> redCheckers;
	private ArrayList<String> blackCheckers;
	private String checkerColor;
	private Checker checker;
	private int count;
	
	@Before
	public void setup() {
		try {
			this.game = new Game();
			this.board = new Board();
			board.createBoardIterator();
			this.boardIterator = board.iterator();
			board.initializeGame();
			this.squares = new ArrayList<Checker>();
			this.squareIterator = new ArrayList<Square>();
			this.playerController = new PlayerController();
			this.game.setPlayerController(playerController);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.redCheckers = new ArrayList<String>();
		this.blackCheckers = new ArrayList<String>();
		board = new Board();
		board.createBoardIterator();
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
		this.blackCheckers = null;
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
		case "black":
			blackCheckers.add(checkerColor);
			break;
		}
	}

	@Test
	public void initialCheckboardShouldHaveTwelveRedPieces() {
		assertEquals("Checkerboard should have twelve red checkers", 12, redCheckers.size());
	}

	@Test
	public void initialCheckboardShouldHaveTwelveBlackPieces() {
		assertEquals("Checkerboard should have twelve red checkers", 12, blackCheckers.size());
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