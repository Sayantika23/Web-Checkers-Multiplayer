package com.webcheckers.service;

import java.io.IOException;
import java.util.List;

import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Player;

/**
 * The Class PlayerService.
 */
public class PlayerService {
	
	/** The player dao impl. */
	private PlayerDaoImpl playerDaoImpl;
	
	/**
	 * Instantiates a new player service.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PlayerService() throws IOException {
		playerDaoImpl = new PlayerDaoImpl();
	}
	
	/**
	 * Save player.
	 *
	 * @param player the player
	 */
	public void savePlayer(Player player) {
		playerDaoImpl.savePlayer(player);
	}

	/**
	 * Save player.
	 *
	 * @param player the player
	 */
	public void savePlayerStatus(Player player, boolean status) {
		playerDaoImpl.savePlayerStatus(player, status);
	}

	/**
	 * Find player.
	 *
	 * @param player the player
	 * @return the player
	 */
	public Player findPlayer(Player player) {
		Player existingPlayer = playerDaoImpl.findPlayerByUsername(player.getUsername());
		return existingPlayer;
	}

	/**
	 * Authenticate.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean authenticate(Player player){
		if (player.getUsername().isEmpty() || player.getPassword().isEmpty()){
			return false;
		}

		Player pl = playerDaoImpl.findPlayerByUsername(player.getUsername());
		if (pl == null) {
			return false;
		}
		boolean passwordsMatch = playerDaoImpl.passwordsMatch(player);
		return passwordsMatch;
	}

	public List<String> getPlayersQueue(){
		List<String> players = playerDaoImpl.getPlayersQueue();
		return players;
	}
}
