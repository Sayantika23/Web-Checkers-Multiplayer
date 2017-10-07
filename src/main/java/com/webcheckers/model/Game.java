package com.webcheckers.model;

import com.webcheckers.controller.GuiController;

import java.io.IOException;
import java.util.UUID;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.PlayerController;

public class Game {
	
	private String gameId;
	private GamePlayController gamePlayController;
	private GuiController guiController;
	private PlayerController playerController;
	
	public Game() throws IOException {
		this.gameId = generateString();
		this.gamePlayController = new GamePlayController();
		this.guiController = new GuiController();
		this.playerController = new PlayerController();
	}
	
	public String generateString() {
        String uuid = UUID.randomUUID().toString();
        return "uuid = " + uuid;
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

	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}
}
