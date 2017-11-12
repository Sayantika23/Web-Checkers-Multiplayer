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
	 * Save player Status.
	 *
	 * @param player the player
	 *               status
	 */
	public void savePlayerStatus(Player player, boolean status) {
		playerDaoImpl.savePlayerStatus(player, status);
	}


	/**
	 * delete player status.
	 *
	 * @param player the player
	 */
	public void deletePlayerStatus(Player player) {
		playerDaoImpl.deletePlayerStatus(player);
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

	public List<String> getPlayersQueue(Player player){
		List<String> players = playerDaoImpl.getPlayersQueue(player);
		return players;
	}

	public void requestOpponent(Player requester, Player player){
		Player pl = playerDaoImpl.findPlayerByUsername(player.getUsername());
		if (pl == null) {
			return;
		}
		playerDaoImpl.requestOpponent(requester, pl);
		return;
	}

	public void registerOpponent(Player player, Player opponent){
		Player pl = playerDaoImpl.findPlayerByUsername(opponent.getUsername());
		if (pl == null) {
			return;
		}
		playerDaoImpl.registerOpponent(player, pl);
		return;
	}

	public List<String> checkRequest(Player player){
		return playerDaoImpl.checkRequest( player);
	}

	public boolean checkRequestAcceptance(Player requester, Player player){
		Player pl = playerDaoImpl.findPlayerByUsername(player.getUsername());
		if (pl == null) {
			return false;
		}
		return playerDaoImpl.checkRequestAcceptance(requester, pl);
	}

	public void deletePlayerRequests(Player player) {
		playerDaoImpl.deletePlayerRequests(player);
	}

	public void deletePlayerOpponentRecords(Player player) {
		playerDaoImpl.deletePlayerOpponentRecords(player);
	}
	
	public void updateScore(Player player) {
		playerDaoImpl.updateScore(player);
	}
}
