package com.webcheckers.controller;

import com.webcheckers.service.PlayerService;

public class PlayerController {

	private PlayerService playerService;

	public PlayerController() {
		this.playerService = new PlayerService();
	}
	public PlayerService getPlayerService() {
		return this.playerService;
	}
}