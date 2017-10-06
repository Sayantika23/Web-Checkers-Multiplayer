package com.webcheckers.controller;

import com.webcheckers.model.Game;
import com.webcheckers.service.PlayerService;

public class PlayerController {
	private PlayerService playerService;

	public PlayerController() {
		playerService = new PlayerService();
	}
	public PlayerService getPlayerService() {
		return playerService;
	}

}
