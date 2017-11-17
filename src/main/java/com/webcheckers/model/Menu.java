package com.webcheckers.model;

/**
 * The Class Menu.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Menu {
	
	private int playerOneScore = 0;
	
	private int playerTwoScore = 0;

	public int getPlayerOneScore() {
		return playerOneScore;
	}

	public int getPlayerTwoScore() {
		return playerTwoScore;
	}

	public void setPlayerOneScore(int playerOneScore) {
		this.playerOneScore = playerOneScore;
	}

	public void setPlayerTwoScore(int playerTwoScore) {
		this.playerTwoScore = playerTwoScore;
	}
}
