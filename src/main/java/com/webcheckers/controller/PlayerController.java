package com.webcheckers.controller;

import java.io.IOException;

import com.webcheckers.service.PlayerService;

/**
 * The Class PlayerController.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class PlayerController {
	
	private PlayerService playerService;

	/**
	 * Instantiates a new player controller.
	 * 
	 * Creates new player service to perform
	 * persistence operations on player domain
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PlayerController() throws IOException {
		this.playerService = new PlayerService();
	}
	
	public PlayerService getPlayerService() {
		return this.playerService;
	}
}