package com.webcheckers.controller;

import java.io.IOException;

import com.webcheckers.service.PlayerService;

/**
 * The Class PlayerController.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class PlayerController {
	
	/** The player service. */
	private PlayerService playerService;

	/**
	 * Instantiates a new player controller.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PlayerController() throws IOException {
		this.playerService = new PlayerService();
	}
	
	/**
	 * Gets the player service.
	 *
	 * @return the player service
	 */
	public PlayerService getPlayerService() {
		return this.playerService;
	}
}