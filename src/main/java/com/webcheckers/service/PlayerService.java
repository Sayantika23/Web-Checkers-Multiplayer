package com.webcheckers.service;

import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Player;

public class PlayerService {
	
	private PlayerDaoImpl playerDao;
	public PlayerService() {
		playerDao = new PlayerDaoImpl();
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

	public boolean authenticate(Player player){
		if (player.getUsername().isEmpty() || player.getPassword().isEmpty()){
			return false;
		}

		Player pl = playerDao.findPlayerByUsername(player.getUsername());
		if (pl == null) {
			return false;
		}
		return player.getPassword().equals(playerDao.getPassword(player.getUsername()));
	}
}
