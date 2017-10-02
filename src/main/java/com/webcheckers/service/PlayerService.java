package com.webcheckers.service;

import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Player;

public class PlayerService {
	private PlayerDaoImpl playerDao;
	PlayerService() {
		
	}
	public void save(Player player) {
		if (checkForPlayer(player) != null) {
			
		}
	}
	public void savePlayer(Player player) {
		playerDao.savePlayer(player);
	}
	public Player checkForPlayer(Player player) {
		Player existingPlayer = null;
		existingPlayer = playerDao.findByUsername(player.getUsername());
		return existingPlayer;
	}
}
