package com.webcheckers.model;

import javax.swing.text.Position;

/**
 * The Class Menu.
 */
public class Menu implements GUI {
	
	/** The player one score. */
	private int playerOneScore = 0;
	
	/** The player two score. */
	private int playerTwoScore = 0;

	/* (non-Javadoc)
	 * @see com.webcheckers.model.GUI#draw(javax.swing.text.Position)
	 */
	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
	}

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
