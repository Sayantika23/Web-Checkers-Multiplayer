package com.webcheckers.dao;

import com.webcheckers.model.Player;
import java.util.List;

/**
 * The Interface PlayerDao.
 */
public interface PlayerDao {
	
	/**
	 * Find player by username.
	 *
	 * @param username the username
	 * @return the player
	 */
	Player findPlayerByUsername(String username);
	
	/**
	 * Save player.
	 *
	 * @param player the player
	 */
	void savePlayer(Player player);
	
	/**
	 * Passwords match.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	boolean passwordsMatch(Player player);

	/**
	 * Save Player Status
	 * @param player
	 */
	void savePlayerStatus(Player player, boolean status);

	List<String> getPlayersQueue(Player player);

	void deletePlayerStatus(Player player);
}