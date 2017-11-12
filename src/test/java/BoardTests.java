//import com.webcheckers.model.Board;
//import com.webcheckers.model.Move;
//import javafx.util.Pair;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static junit.framework.TestCase.assertNull;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
///**
// * The Class CheckerTests.
// *
// * @author <a href='mailto:kk3671@rit.edu'>Kishan K C</a>
// */
//public class BoardTests {
//
//    /** The game. */
//    private Board board;
//    private Board board1;
//    private Board board2;
//    private int[][] boardSetup;
//    private int numRed = 12;
//    private int numBlack = 12;
//    private int numRedKing = 0;
//    private int numBlackKing = 0;
//    private int[][] emptyBoard = new int[][]{
//            { 0, 0, 0, 0, 0, 0, 0, 0},
//            { 0, 0, 0, 0, 0, 0, 0, 0},
//            { 0, 0, 0, 0, 0, 0, 0, 0},
//            { 0, 0, 0, 0, 0, 0, 0, 0},
//            { 0, 0, 0, 0, 0, 0, 0, 0},
//            { 0, 0, 0, 0, 0, 0, 0, 0},
//            { 0, 0, 0, 0, 0, 0, 0, 0},
//            { 0, 0, 0, 0, 0, 0, 0, 0}
//    };
//
//
//    @Before
//    public void setup() {
//        this.board = new Board();
//        this.boardSetup = board.board;
//        this.board1 = new Board(boardSetup,numRed, numBlack);
//        this.board2 = new Board(boardSetup,numRed, numBlack, numRedKing, numBlackKing);
//    }
//
//    @After
//    public void destroy() {
//    	this.board = null;
//        this.board1 = null;
//        this.board2 = null;
//        this.boardSetup = null;
//    }
//
//    /**
//     * Red Checkers should not be null at initial stage of game.
//     */
//    @Test
//    public void RedCheckerShouldNotBeNullatStart() {
//        int redCheckers = board.getNumRed();
//        assertNotNull("Red Checkers must not be null at Start", redCheckers);
//    }
//
//    /**
//     * Red King should be null at initial stage of game.
//     */
//    @Test
//    public void RedKingShouldBeNullatStart() {
//        int redKing = board.getNumRedKing();
//        assertEquals(0, redKing);
//    }
//
//    /**
//     * Black Checkers should not be null at initial stage of game.
//     */
//    @Test
//    public void BlackCheckerShouldNotBeNullatStart() {
//        int blackCheckers = board.getNumBlack();
//        assertNotNull("Red Checkers must not be null at Start", blackCheckers);
//    }
//
//    /**
//     * Black King should be null at initial stage of game.
//     */
//    @Test
//    public void BlackKingShouldBeNullatStart() {
//        int blackKing = board.getNumBlackKing();
//        assertEquals(0, blackKing);
//    }
//
//    /**
//     * Score must be equal at start
//     */
//    @Test
//    public void scoreMustBeEqualAtStart() {
//        int redScore = board.getRedWeightedScore();
//        int blackScore = board.getBlackWeightedScore();
//        assertEquals(redScore,blackScore);
//    }
//
//    /**
//     * Player can be set
//     */
//    @Test
//    public void PlayerShouldBeSet() {
//        int player = 2;
//        board.setPlayer(player);
//        assertEquals(player, board.getPlayer());
//    }
//
//    /**
//     * Jumped should be set to false at start
//     */
//    @Test
//    public void JumpShouldBeFalse() {
//        assertEquals(false, board.isJumped());
//    }
//
//
//    /**
//     * Legal forward move should exist for RED checkers
//     */
//    @Test
//    public void LegalForwardMoveExistsForRedCheckers() {
//        ArrayList<Move> moves = board.getLegalMovesForPlayer(2,2);
//        assertNotNull("Checkers at [2,2] should have moves", moves.size());
//        assertEquals(2, moves.size());
//    }
//
//    /**
//     * Legal forward move should exist for Red King checkers
//     */
//    @Test
//    public void LegalForwardMoveExistsForRedKing() {
//        board.board[2][2] = 2; //Red King
//        ArrayList<Move> moves = board.getLegalMovesForPlayer(2,2);
//        assertNotNull("Red King at [2,2] should have moves", moves.size());
//        assertEquals(2, moves.size());
//    }
//
//    /**
//     * Legal forward move should exist for Red player
//     */
//    @Test
//    public void AllLegalForwardMoveExistsForRed() {
//        board.board[3][3] = 2; //Red King
//        board.board[2][2] = 0;
//        ArrayList<Move> moves = board.getAllLegalMovesForColor(1);
//        assertNotNull("Red should have moves", moves.size());
//    }
//
//    /**
//     * Legal forward move should exist for Black checkers
//     */
//    @Test
//    public void LegalForwardMoveExistsForBlackCheckers() {
//        board.setPlayer(3);
//        ArrayList<Move> moves = board.getLegalMovesForPlayer(5,5);
//        assertNotNull("Black Checker at [5,5] should have moves", moves.size());
//        assertEquals(2, moves.size());
//    }
//
//    /**
//     * Legal forward move should exist for Black King checkers
//     */
//    @Test
//    public void LegalForwardMoveExistsForBlackKing() {
//        board.setPlayer(3);
//        board.board[5][5] = 4; //Black King
//        ArrayList<Move> moves = board.getLegalMovesForPlayer(5,5);
//        assertNotNull("Black King at [5,5] should have moves", moves.size());
//        assertEquals(2, moves.size());
//    }
//
//    /**
//     * Legal forward move should exist for Black
//     */
//    @Test
//    public void AllLegalForwardMoveExistsForBlack() {
//        board.board[5][5] = 0;
//        board.board[4][4] = 4;//Black King
//        ArrayList<Move> moves = board.getAllLegalMovesForColor(3);
//        assertNotNull("Black  should have moves", moves.size());
//    }
//
//    /**
//     * Check valid move
//     */
//    @Test
//    public void ValidMoveShouldBeAllowed() {
//        Move move = new Move(2,2,3,3);
//        assertEquals(true, board.isValidMove(move));
//        move = new Move(3,3,3,3);
//        assertEquals(false, board.isValidMove(move));
//    }
//
//    /**
//     * Valid checker is return with right board index
//     */
//    @Test
//    public void ValidCheckerShouldBeReturned() {
//        int checker = boardSetup[0][0];
//        assertEquals(1, checker);
//    }
//
//    /**
//     * No checker exists beyond board scope
//     */
//    @Test
//    public void NoCheckerShouldBeReturnedOutsideBoard() {
//        int checker = board.getInfoAtPosition(10, 10);
//        assertEquals(0, checker);
//    }
//
//    /**
//     * Initial player should be RED
//     */
//    @Test
//    public void RedShouldBeStartingPlayer() {
//        int player = board.getPlayer();
//        assertEquals(1, player);
//    }
//
//    /**
//     * Checker Should move
//     */
//    @Test
//    public void CheckerShouldMoveToValidPosition() {
//        Move move = new Move(2,2,3,3);
//        int temp = board.board[2][2];
//        boolean validMove = board.isValidMove(move);
//        boolean makeKing = false;
//        if(validMove){
//            makeKing = board.movePiece(move);
//        }
//        assertEquals(false, makeKing);
//
//        assertEquals(0, board.board[2][2]);
//
//        assertEquals(temp, board.board[3][3]);
//
//    }
//
//    /**
//     * Red King Should be made when Red checker move to 7th row
//     */
//    @Test
//    public void RedKingIsMade() {
//        emptyBoard[6][6] = 1;
//        board.board = emptyBoard;
//
//        int initialRedKing = board.getNumRedKing();
//        Move move = new Move(6,6,7,7);
//
//        boolean validMove = board.isValidMove(move);
//        boolean makeKing = false;
//
//        if(validMove){
//            makeKing = board.movePiece(move);
//        }
//        int finalRedKing = board.getNumRedKing();
//
//        assertEquals(true, makeKing);
//
//        assertEquals(0, board.board[6][6]);
//
//        assertEquals(2, board.board[7][7]);
//
//        assertEquals(initialRedKing + 1, finalRedKing);
//
//    }
//
//    /**
//     * Black King Should be made when Red checker move to 0th row
//     */
//    @Test
//    public void BlackKingIsMade() {
//        emptyBoard[1][1] = 3;
//
//        board.board = emptyBoard;
//        board.setPlayer(3);
//        int initialBlackKing = board.getNumBlackKing();
//        Move move = new Move(1,1,0,0);
//
//        boolean validMove = board.isValidMove(move);
//        boolean makeKing = false;
//
//        if(validMove){
//            makeKing = board.movePiece(move);
//        }
//        int finalBlackKing = board.getNumBlackKing();
//
//        assertEquals(true, makeKing);
//
//        assertEquals(0, board.board[1][1]);
//
//        assertEquals(4, board.board[0][0]);
//
//        assertEquals(initialBlackKing + 1, finalBlackKing);
//    }
//
//    /**
//     * Jump is handled for Red checker
//     */
//    @Test
//    public void JumpIsHandledForRed() {
//        emptyBoard[1][1] = 3;
//        emptyBoard[0][0] = 1;
//
//        Move move = new Move(0,0,2,2);
//
//        board.board = emptyBoard;
//        int numBlack = board.getNumBlack();
//        board.movePiece(move);
//        board.handleJump(move);
//
//        int finalNumBlack = board.getNumBlack();
//
//        assertEquals(true, board.isJumped());
//
//        // Black Checker should be removed
//        assertEquals(0, board.board[1][1]);
//
//        //Starting positin of jump is empty
//        assertEquals(0, board.board[0][0]);
//
//        //Ending positin of jump is Red
//        assertEquals(1, board.board[2][2]);
//
//        // Black checker count is decreased by 1
//        assertEquals(numBlack - 1, finalNumBlack);
//    }
//
//    /**
//     * Jump is handled for Black checker
//     */
//    @Test
//    public void JumpIsHandledForBlack() {
//        emptyBoard[0][0] = 0;
//        emptyBoard[1][1] = 1;
//        emptyBoard[2][2] = 3;
//
//        Move move = new Move(2,2,0,0);
//
//        board.board = emptyBoard;
//        board.setPlayer(3);
//        int numRed = board.getNumRed();
//        board.movePiece(move);
//        board.handleJump(move);
//
//        int finalNumRed = board.getNumRed();
//
//        assertEquals(true, board.isJumped());
//
//        // Red Checker should be removed
//        assertEquals(0, board.board[1][1]);
//
//        //Starting positin of jump is empty
//        assertEquals(0, board.board[2][2]);
//
//        //Ending position of jump is Black
//        assertEquals(4, board.board[0][0]);
//
//        // Red checker count is decreased by 1
//        assertEquals(numRed - 1, finalNumRed);
//    }
//
//    /**
//     * Jump is handled for Red checker over Black King
//     */
//    @Test
//    public void JumpIsHandledForRedOverBlackKing() {
//        emptyBoard[1][1] = 4;
//        emptyBoard[0][0] = 1;
//        this.board = new Board(emptyBoard,1, 1, 0, 1);
//
//        numBlackKing = board.getNumBlackKing();
//        Move move = new Move(0,0,2,2);
//        board.movePiece(move);
//        board.handleJump(move);
//
//        int finalNumBlackKing = board.getNumBlackKing();
//
//        assertEquals(true, board.isJumped());
//
//        // Black Checker should be removed
//        assertEquals(0, board.board[1][1]);
//
//        //Starting positin of jump is empty
//        assertEquals(0, board.board[0][0]);
//
//        //Ending positin of jump is Red
//        assertEquals(1, board.board[2][2]);
//
//        // Black checker count is decreased by 1
//        assertEquals(0, finalNumBlackKing);
//    }
//
//
//    /**
//     * Jump is handled for Black checker over Red King
//     */
//    @Test
//    public void JumpIsHandledForBlackOverRedKing() {
//        emptyBoard[0][0] = 0;
//        emptyBoard[1][1] = 2;
//        emptyBoard[2][2] = 3;
//        this.board = new Board(emptyBoard,1, 1, 1, 0);
//        board.setPlayer(3);
//
//        numRedKing = board.getNumRedKing();
//        Move move = new Move(2,2,0,0);
//        board.movePiece(move);
//        board.handleJump(move);
//
//        int finalNumRedKing = board.getNumRedKing();
//
//        assertEquals(true, board.isJumped());
//
//        // Red Checker should be removed
//        assertEquals(0, board.board[1][1]);
//
//        //Starting positin of jump is empty
//        assertEquals(0, board.board[2][2]);
//
//        //Ending position of jump is Black
//        assertEquals(4, board.board[0][0]);
//
//        // Red checker count is decreased by 1
//        assertEquals(numRedKing - 1, finalNumRedKing);
//    }
//
//    /**
//     * Jumps available for Red
//     */
//    @Test
//    public void JumpIsHandledForRedChecker() {
//        emptyBoard[3][3] = 1;
//        emptyBoard[4][4] = 3;
//        emptyBoard[4][2] = 3;
//        board.board=emptyBoard;
//        int row = 3, col = 3;
//        ArrayList<Move> jumps = board.getJumps(row, col);
//        assertEquals(2, jumps.size());
//
//    }
//
//    /**
//     * Jumps available for Black
//     */
//    @Test
//    public void JumpIsHandledForBlackChecker() {
//        emptyBoard[3][3] = 3;
//        emptyBoard[2][2] = 1;
//        emptyBoard[2][4] = 1;
//        board.board=emptyBoard;
//        board.setPlayer(3);
//        int row = 3, col = 3;
//        ArrayList<Move> jumps = board.getJumps(row, col);
//        assertEquals(2, jumps.size());
//
//    }
//
//    /**
//     * Jumps available for Red King
//     */
//    @Test
//    public void JumpIsHandledForRedKing() {
//        emptyBoard[3][3] = 2;
//        emptyBoard[4][4] = 3;
//        emptyBoard[4][2] = 3;
//        emptyBoard[2][4] = 3;
//        emptyBoard[2][2] = 3;
//        board.board=emptyBoard;
//        int row = 3, col = 3;
//        ArrayList<Move> jumps = board.getJumps(row, col);
//        assertEquals(4, jumps.size());
//
//    }
//
//    /**
//     * Jumps available for Black King
//     */
//    @Test
//    public void JumpIsHandledForBlackKing() {
//        emptyBoard[3][3] = 4;
//        emptyBoard[2][2] = 1;
//        emptyBoard[2][4] = 1;
//        emptyBoard[4][4] = 1;
//        emptyBoard[4][2] = 1;
//
//
//        board.board=emptyBoard;
//        board.setPlayer(3);
//        int row = 3, col = 3;
//        ArrayList<Move> jumps = board.getJumps(row, col);
//        assertEquals(4, jumps.size());
//
//    }
//
//}