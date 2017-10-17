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
	
	/** The game id. */
	private String gameId;
	
	/** The game play controller. */
	private GamePlayController gamePlayController;
	
	/** The gui controller. */
	private GuiController guiController;
	
	/** The player controller. */
	private PlayerController playerController;
	
	/**
	 * Instantiates a new game.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Game() throws IOException {
		this.gameId = generateString();
		this.gamePlayController = new GamePlayController();
		this.guiController = new GuiController();
		this.playerController = new PlayerController();
	}
	
	/**
	 * Generate string.
	 *
	 * @return the string
	 */
	public String generateString() {
        String uuid = UUID.randomUUID().toString();
        return "uuid = " + uuid;
    }
	
	/**
	 * Gets the game id.
	 *
	 * @return the game id
	 */
	public String getGameId() {
		return gameId;
	}
	
	/**
	 * Gets the GUI controller.
	 *
	 * @return the GUI controller
	 */
	public GuiController getGUIController() {
		return guiController;
	}
	
	/**
	 * Gets the game play controller.
	 *
	 * @return the game play controller
	 */
	public GamePlayController getGamePlayController() {
		return gamePlayController;
	}

	/**
	 * Gets the player controller.
	 *
	 * @return the player controller
	 */
	public PlayerController getPlayerController() {
		return playerController;
	}

	/**
	 * Sets the player controller.
	 *
	 * @param playerController the new player controller
	 */
	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}
}
