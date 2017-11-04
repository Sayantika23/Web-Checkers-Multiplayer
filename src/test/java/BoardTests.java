import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The Class CheckerTests.
 *
 * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
 */
public class BoardTests {

    /** The game. */
    private Board board;
    private int[][] boardSetup;

    @Before
    public void setup() {
    	this.board = new Board();
    	this.boardSetup = board.board;
    }

    @After
    public void destroy() {
    	this.board = null;
    }

    /**
     * Red Checkers should not be null at initial stage of game.
     */
    @Test
    public void RedCheckerShouldNotBeNullatStart() {
        int redCheckers = board.getNumRed();
        assertNotNull("Red Checkers must not be null at Start", redCheckers);
    }

    /**
     * Red King should be null at initial stage of game.
     */
    @Test
    public void RedKingShouldBeNullatStart() {
        int redKing = board.getNumRedKing();
        assertEquals(0, redKing);
    }

    /**
     * Black Checkers should not be null at initial stage of game.
     */
    @Test
    public void BlackCheckerShouldNotBeNullatStart() {
        int blackCheckers = board.getNumBlack();
        assertNotNull("Red Checkers must not be null at Start", blackCheckers);
    }

    /**
     * Black King should be null at initial stage of game.
     */
    @Test
    public void BlackKingShouldBeNullatStart() {
        int blackKing = board.getNumBlackKing();
        assertEquals(0, blackKing);
    }

    /**
     * Score must be equal at start
     */
    @Test
    public void scoreMustBeEqualAtStart() {
        int redScore = board.getRedWeightedScore();
        int blackScore = board.getBlackWeightedScore();
        assertEquals(redScore,blackScore);
    }

    /**
     * Legal forward move should exist for valid checkers
     */
    @Test
    public void LegalForwardMoveExists() {
        ArrayList<Move> moves = board.getLegalMovesForPlayer(2,2);
        System.out.println(board.getInfoAtPosition(2,2));
        assertNotNull("Checkers at [2,2] should have moves", moves.size());
        assertEquals(2, moves.size());
    }

    /**
     * Check valid move
     */
    @Test
    public void ValidMoveShouldBeAllowed() {
        Move move = new Move(2,2,3,3);
        assertEquals(true, board.isValidMove(move));
    }

    /**
     * Valid checker is return with right board index
     */
    @Test
    public void ValidCheckerShouldBeReturned() {
        int checker = boardSetup[0][0];
        assertEquals(1, checker);
    }

    /**
     * No checker exists beyond board scope
     */
    @Test
    public void NoCheckerShouldBeReturnedOutsideBoard() {
        int checker = board.getInfoAtPosition(10, 10);
        assertEquals(0, checker);
    }

    /**
     * Initial player should be RED
     */
    @Test
    public void RedShouldBeStartingPlayer() {
        int player = board.getPlayer();
        assertEquals(1, player);
    }

    /**
     * Checker Should move
     */
    @Test
    public void CheckerShouldMoveToValidPosition() {
        Move move = new Move(2,2,3,3);
        int temp = board.board[2][2];
        boolean validMove = board.isValidMove(move);
        boolean makeKing = false;
        if(validMove){
            makeKing = board.movePiece(move);
        }
        assertEquals(false, makeKing);

        assertEquals(0, board.board[2][2]);

        assertEquals(temp, board.board[3][3]);

    }



}