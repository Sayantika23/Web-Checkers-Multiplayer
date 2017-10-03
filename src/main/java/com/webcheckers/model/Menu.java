package com.webcheckers.model;

import javax.swing.text.Position;

public class Menu implements GUI {
	
	private int playerOneScore = 0;
	private int playerTwoScore = 0;

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
		
	}

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
