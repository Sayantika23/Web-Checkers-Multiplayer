import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webcheckers.model.Board;
import com.webcheckers.model.Checker;
import com.webcheckers.model.Row;
import com.webcheckers.model.Square;

/**
 * The Class CheckerboardTest.
 * 
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class CheckerboardTest {
	
	/** The board iterator. */
	private ArrayList<Row> boardIterator;
	
	/** The squares. */
	private ArrayList<Checker> squares;
	
	/** The square iterator. */
	private ArrayList<Square> squareIterator;
	
	/** The red checkers. */
	private ArrayList<String> redCheckers;
	
	/** The white checkers. */
	private ArrayList<String> blackCheckers;
	
	/** The checker color. */
	private String checkerColor;
	
	/** The checker. */
	private Checker checker;
	
	/** The board. */
	private Board board;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		this.redCheckers = new ArrayList<String>();
		this.blackCheckers = new ArrayList<String>();
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
				if (square.isValid() 
						&& checker.getType().equals("checker")
						&& !checkerColor.equals("transparent")) {
					addToCheckersList(checkerColor);
				}
			}
		}
	}

	/**
	 * Destroy.
	 */
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

	/**
	 * Adds the to checkers list.
	 *
	 * @param checkerColor the checker color
	 */
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

	/**
	 * Initial checkboard should have twelve red pieces.
	 */
	@Test
	public void initialCheckboardShouldHaveTwelveRedPieces() {
		assertEquals("Checkerboard should have twelve red checkers", 12, redCheckers.size());
	}

	/**
	 * Initial checkboard should have twelve white pieces.
	 */
	@Test
	public void initialCheckboardShouldHaveTwelveBlackPieces() {
		assertEquals("Checkerboard should have twelve red checkers", 12, blackCheckers.size());
	}
}
