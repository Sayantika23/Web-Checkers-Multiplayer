package com.webcheckers.model;

import com.webcheckers.controller.GuiController;

import java.io.IOException;
import java.util.UUID;

import com.webcheckers.controller.GamePlayController;
import com.webcheckers.controller.PlayerController;

/**
 * The Class Game.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class Game {
	
	private String gameId;
	private GamePlayController gamePlayController;
	private GuiController guiController;
	private PlayerController playerController;
	private boolean isInitialized;
	
	/**
	 * Instantiates a new game and
	 * generates unique game id and
	 * creates and stores new references
	 * to controllers
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Game() throws IOException {
		this.gameId = generateString();
		this.gamePlayController = new GamePlayController();
		this.guiController = new GuiController();
		this.playerController = new PlayerController();
	}
	
	public boolean isInitialized() {
		return isInitialized;
	}
	
	public void initialize() {
		isInitialized = true;
	}
	
	public void cancelGame() {
		isInitialized = false;
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
	
	public GamePlayController getGamePlayController() {
		return gamePlayController;
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}
}