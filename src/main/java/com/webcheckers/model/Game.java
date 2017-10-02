package com.webcheckers.model;

import com.webcheckers.controller.GUIController;
import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.PlayerController;

public class Game {
	private String gameId = "ASDFASDFADSFASDF";
	private GamePlayController gameController;
	private GUIController guiController;
	private PlayerController playerController;
	Game() {
		
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
		setGameController(new GamePlayController(this));
		setGuiController(new GUIController(this));
		setPlayerController(new PlayerController(this));
	}
	public GamePlayController getGameController() {
		return gameController;
	}
	public void setGameController(GamePlayController gameController) {
		this.gameController = gameController;
	}
	public GUIController dgetGuiController() {
		return guiController;
	}
	public void setGuiController(GUIController guiController) {
		this.guiController = guiController;
	}
	public PlayerController getPlayerController() {
		return playerController;
	}
	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}
}
