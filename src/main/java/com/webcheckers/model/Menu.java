package com.webcheckers.model;

/**
 * The Class Menu.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Menu {
	
	/** The player one score. */
	private int playerOneScore = 0;
	
	/** The player two score. */
	private int playerTwoScore = 0;

	/**
	 * Gets the player one score.
	 *
	 * @return the player one score
	 */
	public int getPlayerOneScore() {
		return playerOneScore;
	}

	/**
	 * Gets the player two score.
	 *
	 * @return the player two score
	 */
	public int getPlayerTwoScore() {
		return playerTwoScore;
	}

	/**
	 * Sets the player one score.
	 *
	 * @param playerOneScore the new player one score
	 */
	public void setPlayerOneScore(int playerOneScore) {
		this.playerOneScore = playerOneScore;
	}

	/**
	 * Sets the player two score.
	 *
	 * @param playerTwoScore the new player two score
	 */
	public void setPlayerTwoScore(int playerTwoScore) {
		this.playerTwoScore = playerTwoScore;
	}
}
