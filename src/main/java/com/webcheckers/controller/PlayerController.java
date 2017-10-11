package com.webcheckers.controller;

import java.io.IOException;

import com.webcheckers.service.PlayerService;

public class PlayerController {
	private PlayerService playerService;

	public PlayerController() throws IOException {
		this.playerService = new PlayerService();
	}
	public PlayerService getPlayerService() {
		return this.playerService;
	}
}