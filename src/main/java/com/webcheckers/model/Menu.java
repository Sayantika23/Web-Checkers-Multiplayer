package com.webcheckers.model;

import javax.swing.text.Position;

public class Menu implements GUI {
	
	private String playerOneScore;
	private String playerTwoScore;

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
		
	}

	public String getPlayerOneScore() {
		return playerOneScore;
	}

	public void setPlayerOneScore(String playerOneScore) {
		this.playerOneScore = playerOneScore;
	}

	public void setPlayerTwoScore(String playerTwoScore) {
		this.playerTwoScore = playerTwoScore;
	}

}
