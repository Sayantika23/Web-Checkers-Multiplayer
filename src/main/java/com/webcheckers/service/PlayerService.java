package com.webcheckers.service;

import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Player;

public class PlayerService {
	private PlayerDaoImpl playerDao;
	PlayerService() {
		
	}
	public void savePlayer(Player player) {
		if (checkForPlayer(player) == null) {
			playerDao.savePlayer(player);
		}
	}
	public Player checkForPlayer(Player player) {
		Player existingPlayer = null;
		existingPlayer = playerDao.findPlayerByUsername(player.getUsername());
		return existingPlayer;
	}
}
