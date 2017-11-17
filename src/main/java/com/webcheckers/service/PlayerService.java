package com.webcheckers.service;

import java.io.IOException;
import java.util.List;

import com.webcheckers.dao.PlayerDaoImpl;
import com.webcheckers.model.Player;

/**
 * The Class PlayerService.
 * 
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class PlayerService {
	
	private PlayerDaoImpl playerDaoImpl;
	
	/**
	 * Instantiates a new player service
	 * and creates new instance of player
	 * dao implementation class
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PlayerService() throws IOException {
		playerDaoImpl = new PlayerDaoImpl();
	}
	
	public void savePlayer(Player player) {
		playerDaoImpl.savePlayer(player);
	}

	public void savePlayerStatus(Player player, boolean status) {
		playerDaoImpl.savePlayerStatus(player, status);
	}

	public void deletePlayerStatus(Player player) {
		playerDaoImpl.deletePlayerStatus(player);
	}

	public Player findPlayer(Player player) {
		Player existingPlayer = playerDaoImpl.findPlayerByUsername(player.getUsername());
		return existingPlayer;
	}

	/**
	 * Authenticate.
	 * 
	 * Authenticates user by checking if
	 * username or password is empty then
	 * finds player by username and checks
	 * if passwords match
	 *
	 * @param player the player
	 * @return boolean
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

	/**
	 * Request opponent.
	 * 
	 * Finds user by player name
	 * and requests opponent
	 *
	 * @param player the player
	 * @param requester the requester
	 */
	public void requestOpponent(Player requester, Player player){
		Player pl = playerDaoImpl.findPlayerByUsername(player.getUsername());
		if (pl == null) {
			return;
		}
		playerDaoImpl.requestOpponent(requester, pl);
		return;
	}
	

	/**
	 * Register opponent.
	 * 
	 * Finds user by player name
	 * and registers opponent
	 *
	 * @param player the player
	 * @parsm opponent the opponent
	 * @return boolean
	 */
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

	/**
	 * Check request acceptance.
	 * 
	 * Finds user by player name
	 * and checks request acceptance
	 *
	 * @param requester the requester
	 * @parsm player the player
	 * @return boolean
	 */
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
