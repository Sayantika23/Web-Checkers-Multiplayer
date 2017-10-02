package com.webcheckers.model;

public class Menu implements GUI {
	
	private String playerOneScore;
	private String playerTwoScore;

	@Override
	public void createGuiElements() {
		// TODO Auto-generated method stub
	}

	public String getPlayerOneScore() {
		return playerOneScore;
	}
	
	public String getPlayerTwoScore() {
		return playerTwoScore;
	}

	public void setPlayerOneScore(String playerOneScore) {
		this.playerOneScore = playerOneScore;
	}

	public void setPlayerTwoScore(String playerTwoScore) {
		this.playerTwoScore = playerTwoScore;
	}

}
