package com.webcheckers.model;

import javafx.util.Pair;

import java.util.ArrayList;

/**
 * The Class Board.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Board {

	/**
	 * Static final integers representing
	 * checker colors for creating 2d
	 * checker array
	 */
	static final int EMPTY = 0, // Value representing an empty square.
			RED = 1, // A regular red piece.
			RED_KING = 2, // A red king.
			BLACK = 3, // A regular black piece.
			BLACK_KING = 4, // A black king.
			INVALID = 5;

	public ArrayList<Row> iterator;
	private final static int NUMBER_OF_ROWS = 8;
	private final static int NUMBER_OF_COLS = 8;
	public static int[][] board = new int[NUMBER_OF_ROWS][NUMBER_OF_COLS];
	private int redCheckerCount = 12;
	private int blackCheckerCount = 12;
	private int redKingCount = 0;
	private int blackKingCount = 0;
	private boolean jumped = false;
	private int player = BLACK;

	public Board() {

	}

	public void createBoardIterator() {
		this.iterator = new ArrayList<Row>();
	}

	public Board(int[][] newBoard, int numRed, int numBlack) {
		board = newBoard;
		this.redCheckerCount = numRed;
		this.blackCheckerCount = numBlack;
	}

	public Board(int[][] newBoard, int numRed, int numBlack, int numRedKing, int numBlackKing) {
		board = newBoard;
		this.redCheckerCount = numRed;
		this.blackCheckerCount = numBlack;
		this.redKingCount = numRedKing;
		this.blackKingCount = numBlackKing;
	}

	/**
	 * Iterator.
	 *
	 * @return the array list of rows
	 */
	public ArrayList<Row> iterator() {
		iterator.clear();
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			Row row = new Row(i, board[i]);
			iterator.add(row);
		}
		return iterator;
	}

	/**
	 * Creates new 2d checkerboard array
	 */
	public void initializeGame() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (row % 2 == col % 2) {
					if (row < 3)
						board[row][col] = RED;
					else if (row > 4)
						board[row][col] = BLACK;
					else
						board[row][col] = EMPTY;
				} else {
					board[row][col] = INVALID;
				}
			}
		}
	}

	public int getNumRed() {
		return redCheckerCount;
	}

	public int getNumRedKing() {
		return redKingCount;
	}

	public int getNumBlack() {
		return blackCheckerCount;
	}

	public int getNumBlackKing() {
		return blackKingCount;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getRedWeightedScore() {
		return redCheckerCount - redKingCount + (3 * redKingCount);
	}

	public int getBlackWeightedScore() {
		return blackCheckerCount - blackKingCount + (3 * blackKingCount);
	}

	public void setPlayer(int color) {
		player = color;
	}

	public int getPlayer() {
		return player;
	}

	public int getInfoAtPosition(int row, int col) {
		if (row < 0 || row > 7 || col < 0 || col > 7) {
			return EMPTY;
		}
		return board[row][col];
	}

	public ArrayList<Move> getLegalMovesForPlayer(int row, int col) {
		return getLegalMovesForColorAtPosition(player, row, col);
	}

	/**
	 * Get all the legal moves for the given color at the given location
	 * 
	 * @param color
	 * @param row
	 * @param col
	 * 
	 * @return all legal moves at position row, col for color
	 */
	public ArrayList<Move> getLegalMovesForColorAtPosition(int color, int row, int col) {
		int chosenPiece = getInfoAtPosition(row, col);
		ArrayList<Move> moves = new ArrayList<>();

		// Get black checker moves
		if (player == BLACK) {
			if (color == BLACK) {
				if (chosenPiece == BLACK || chosenPiece == BLACK_KING) {
					if (getInfoAtPosition(row - 1, col - 1) == EMPTY)
						moves.add(new Move(row, col, row - 1, col - 1));
					if (getInfoAtPosition(row - 1, col + 1) == EMPTY)
						moves.add(new Move(row, col, row - 1, col + 1));
				}
				if (chosenPiece == BLACK_KING) {
					if (getInfoAtPosition(row + 1, col - 1) == EMPTY)
						moves.add(new Move(row, col, row + 1, col - 1));
					if (getInfoAtPosition(row + 1, col + 1) == EMPTY)
						moves.add(new Move(row, col, row + 1, col + 1));

				}
			} else if (color == RED) { // Get black moves
				if (chosenPiece == RED || chosenPiece == RED_KING) {
					if (getInfoAtPosition(row + 1, col - 1) == EMPTY)
						moves.add(new Move(row, col, row + 1, col - 1));
					if (getInfoAtPosition(row + 1, col + 1) == EMPTY)
						moves.add(new Move(row, col, row + 1, col + 1));
				}
				if (chosenPiece == RED_KING) {
					if (getInfoAtPosition(row - 1, col - 1) == EMPTY)
						moves.add(new Move(row, col, row - 1, col - 1));
					if (getInfoAtPosition(row - 1, col + 1) == EMPTY)
						moves.add(new Move(row, col, row - 1, col + 1));
				}
			}
		// Get red checker moves
		} else if (player == RED) {
			if (color == RED) {
				if (chosenPiece == RED || chosenPiece == RED_KING) {
					if (getInfoAtPosition(row + 1, col + 1) == EMPTY)
						moves.add(new Move(row, col, row + 1, col + 1));
					if (getInfoAtPosition(row + 1, col - 1) == EMPTY)
						moves.add(new Move(row, col, row + 1, col - 1));

				}
				if (chosenPiece == RED_KING) {
					if (getInfoAtPosition(row - 1, col + 1) == EMPTY)
						moves.add(new Move(row, col, row - 1, col + 1));
					if (getInfoAtPosition(row - 1, col - 1) == EMPTY)
						moves.add(new Move(row, col, row - 1, col - 1));

				}
			} else if (color == BLACK) { // Get black moves
				if (chosenPiece == BLACK || chosenPiece == BLACK_KING) {
					if (getInfoAtPosition(row - 1, col + 1) == EMPTY)
						moves.add(new Move(row, col, row - 1, col + 1));
					if (getInfoAtPosition(row - 1, col - 1) == EMPTY)
						moves.add(new Move(row, col, row - 1, col - 1));
				}
				if (chosenPiece == BLACK_KING) {
					if (getInfoAtPosition(row + 1, col + 1) == EMPTY)
						moves.add(new Move(row, col, row + 1, col + 1));
					if (getInfoAtPosition(row + 1, col - 1) == EMPTY)
						moves.add(new Move(row, col, row + 1, col - 1));
				}
			}
		}

		// Add jumps
		ArrayList<Move> jumps = getJumps(row, col);
		moves.addAll(jumps);
		return moves;
	}

	/**
	 * Get all possible moves for the given color
	 * 
	 * @param color
	 * 
	 * @return all legal moves for color
	 */
	public ArrayList<Move> getAllLegalMovesForColor(int color) {
		ArrayList<Move> moves = new ArrayList<>();
		int count = 0;

		// Loop through board and get moves at each location
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				int currPosition = getInfoAtPosition(row, col);
				if (currPosition == color) {
					moves.addAll(getLegalMovesForColorAtPosition(color, row, col));
					count++;
				}

				// Get king moves
				if (color == RED && currPosition == RED_KING) {
					moves.addAll(getLegalMovesForColorAtPosition(color, row, col));
					count++;
				} else if (color == BLACK && currPosition == BLACK_KING) {
					moves.addAll(getLegalMovesForColorAtPosition(color, row, col));
					count++;
				}

				// Stop if all the pieces of the color have been found
				if (count == 12) {
					return moves;
				}
			}
		}
		return moves;
	}

	public void removePiece(Move move) {
		board[move.currRow][move.currCol] = EMPTY;
	}

	/**
	 * Moves a piece
	 * 
	 * @param move
	 * 
	 * @return boolean for whether or not a king was made
	 */
	public boolean movePiece(Move move) {
		// Changes location of piece
		int temp = board[move.currRow][move.currCol];
		board[move.currRow][move.currCol] = EMPTY;

		// handles king
		if (player == RED && move.movRow == 7) {
			board[move.movRow][move.movCol] = RED_KING;
			redKingCount += 1;
			return true;
		} else if (player == BLACK && move.movRow == 0) {
			board[move.movRow][move.movCol] = BLACK_KING;
			blackKingCount += 1;
			return true;
		} else {
			board[move.movRow][move.movCol] = temp;
			return false;
		}
	}

	/**
	 * Get all the legal jumps from the given location
	 * 
	 * @param row
	 * @param col
	 * 
	 * @return an array list of all legal jumps
	 */
	public ArrayList<Move> getJumps(int row, int col) {
		ArrayList<Move> jumps = new ArrayList<>();
		int chosenPiece = getInfoAtPosition(row, col);

		// Get red jumps
		if (player == RED) {
			if (chosenPiece == RED || chosenPiece == RED_KING) {
				if (getInfoAtPosition(row + 1, col + 1) == BLACK || getInfoAtPosition(row + 1, col + 1) == BLACK_KING) {
					if (getInfoAtPosition(row + 2, col + 2) == EMPTY) {
						jumps.add(new Move(row, col, row + 2, col + 2));
					}
				}
				if (getInfoAtPosition(row + 1, col - 1) == BLACK || getInfoAtPosition(row + 1, col - 1) == BLACK_KING) {
					if (getInfoAtPosition(row + 2, col - 2) == EMPTY) {
						jumps.add(new Move(row, col, row + 2, col - 2));
					}
				}
			}

			// Get backward jumps
			if (chosenPiece == RED_KING) {
				if (getInfoAtPosition(row - 1, col + 1) == BLACK || getInfoAtPosition(row - 1, col + 1) == BLACK_KING) {
					if (getInfoAtPosition(row - 2, col + 2) == EMPTY) {
						jumps.add(new Move(row, col, row - 2, col + 2));
					}
				}
				if (getInfoAtPosition(row - 1, col - 1) == BLACK || getInfoAtPosition(row - 1, col - 1) == BLACK_KING) {
					if (getInfoAtPosition(row - 2, col - 2) == EMPTY) {
						jumps.add(new Move(row, col, row - 2, col - 2));
					}
				}
			}
		} else if (player == BLACK) { // Get black jumps
			if (chosenPiece == BLACK || chosenPiece == BLACK_KING) {
				if (getInfoAtPosition(row - 1, col + 1) == RED || getInfoAtPosition(row - 1, col + 1) == RED_KING) {
					if (getInfoAtPosition(row - 2, col + 2) == EMPTY) {
						jumps.add(new Move(row, col, row - 2, col + 2));
					}
				}
				if (getInfoAtPosition(row - 1, col - 1) == RED || getInfoAtPosition(row - 1, col - 1) == RED_KING) {
					if (getInfoAtPosition(row - 2, col - 2) == EMPTY) {
						jumps.add(new Move(row, col, row - 2, col - 2));
					}
				}
			}

			// Get backwards jumps
			if (chosenPiece == BLACK_KING) {
				if (getInfoAtPosition(row + 1, col + 1) == RED || getInfoAtPosition(row + 1, col + 1) == RED_KING) {
					if (getInfoAtPosition(row + 2, col + 2) == EMPTY) {
						jumps.add(new Move(row, col, row + 2, col + 2));
					}
				}
				if (getInfoAtPosition(row + 1, col - 1) == RED || getInfoAtPosition(row + 1, col - 1) == RED_KING) {
					if (getInfoAtPosition(row + 2, col - 2) == EMPTY) {
						jumps.add(new Move(row, col, row + 2, col - 2));
					}
				}
			}
		}
		return jumps;
	}

	/**
	 * Deletes the piece that was jumped over
	 * 
	 * @param move
	 */
	public void handleJump(Move move) {
		Pair<Integer, Integer> spaceSkipped = move.getSpaceInbetween();

		// Verifies that jump was made
		if (spaceSkipped.getKey() != move.currRow && spaceSkipped.getKey() != move.movRow
				&& spaceSkipped.getValue() != move.movCol && spaceSkipped.getValue() != move.currCol) {
			if (board[spaceSkipped.getKey()][spaceSkipped.getValue()] == RED_KING) {
				redKingCount -= 1;
			}
			if (board[spaceSkipped.getKey()][spaceSkipped.getValue()] == BLACK_KING) {
				blackKingCount -= 1;
			}
			board[spaceSkipped.getKey()][spaceSkipped.getValue()] = EMPTY;
			jumped = true;
			if (player == RED) {
				blackCheckerCount -= 1;

			} else {
				redCheckerCount -= 1;
			}
		} else {
			jumped = false;
		}
	}
	
	/**
	 * Checks if current move is valid or not
	 * 
	 * @param move
	 * @return boolean for valid move
	 */
	public boolean isValidMove(Move move) {
		ArrayList<Move> moves = this.getLegalMovesForPlayer(move.currRow, move.currCol);
		if (moves.contains(move)) {
			return true;
		}
		return false;
	}

}
