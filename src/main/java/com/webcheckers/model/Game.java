package com.webcheckers.model;

import com.webcheckers.controller.GUIController;
import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.PlayerController;

public class Game {
	private String gameId = "ASDFASDFADSFASDF";
	private GamePlayController gameController;
	private GUIController guiController;
	private PlayerController playerController;
	public Game() {
		
	}
	public void initialize() {
		
	}
	public String generateGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getGameId() {
		return gameId;
	}
	public void instantiateControllers() {
		gameController = new GamePlayController(this);
		guiController = new GUIController(this);
		playerController = new PlayerController(this);
	}
	public GUIController getGUIController() {
		return guiController;
	}
}
