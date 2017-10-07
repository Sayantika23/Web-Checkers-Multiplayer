package com.webcheckers.service;

import java.io.IOException;

import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Player;

public class PlayerService {
	
	private PlayerDaoImpl playerDaoImpl;
	public PlayerService() throws IOException {
		playerDaoImpl = new PlayerDaoImpl();
	}
	
	public void savePlayer(Player player) {
//		if (checkForPlayer(player) == null) {
			playerDaoImpl.savePlayer(player);
//		}
	}
	
	public Player findPlayer(Player player) {;
		Player existingPlayer = playerDaoImpl.findPlayerByUsername(player.getUsername());
		return existingPlayer;
	}

	public boolean authenticate(Player player){
		if (player.getUsername().isEmpty() || player.getPassword().isEmpty()){
			return false;
		}

		Player pl = playerDaoImpl.findPlayerByUsername(player.getUsername());
		if (pl == null) {
			return false;
		}
		boolean passwordsMatch = player.getPassword()
				.equals(playerDaoImpl.getPassword(player.getUsername()));
		return passwordsMatch;
	}
}
