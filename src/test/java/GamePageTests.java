import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.model.Board;
import com.webcheckers.model.Checker;
import com.webcheckers.model.Game;
import com.webcheckers.model.Row;
import com.webcheckers.model.Square;
import com.webcheckers.ui.GameController;

public class GamePageTests {

	private Game game;
	private GameController gameController;
	private GamePlayController gamePlayController;
	private Board board;
	private ArrayList<Row> boardIterator;
	private ArrayList<Square> squareIterator;
	private ArrayList<Checker> squares;

	public GamePageTests() {
		try {
			this.game = new Game();
			this.gameController = new GameController(game);
			this.gamePlayController = game.getGamePlayController();
			this.board = gamePlayController.getBoard();
			this.boardIterator = board.iterator();
			this.squares = new ArrayList<Checker>();
			this.squareIterator = new ArrayList<Square>();
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
		assertEquals("Board must have 8 rows", 8, boardIterator.size());
	}

	@Test
	public void gameControlleBoardShouldHaveSixtyFourSquares() {
		for (Row row : boardIterator) {
			squareIterator = row.iterator();
			for (Square square : squareIterator) {
				squares.add(square.getPiece());
			}
		}
		assertEquals("Board must have 64 squares", 64, squares.size());
	}
	@Test
	public void gameControllerBoardShouldHaveThirtyTwoValidSquares() {
		int count = 0;
		for (Row row : boardIterator) {
			squareIterator = row.iterator();
			for (Square square : squareIterator) {
				if (square.isValid()) {
					count++;
				}
			}
		}
		assertEquals("Board must have 64 squares", 32, count);
	}
}