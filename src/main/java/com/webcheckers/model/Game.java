package com.webcheckers.model;

import com.webcheckers.controller.GuiController;
import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.PlayerController;

public class Game {
	private String gameId = "ASDFASDFADSFASDF";
	private GamePlayController gamePlayController;
	private GuiController guiController;
	private PlayerController playerController;
	public Game() {
		
	}
	public void initialize() {
		gamePlayController = new GamePlayController(this);
		guiController = new GuiController(this);
		playerController = new PlayerController(this);
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
	public GuiController getGUIController() {
		return guiController;
	}
	public GamePlayController getGameController() {
		return gamePlayController;
	}
	public PlayerController getPlayerController() {
		return playerController;
	}
}
