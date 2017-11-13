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

	/**
	 * @param player
	 * @return
	 */
	List<String> getPlayersQueue(Player player);

	/**
	 * @param player
	 */
	void deletePlayerStatus(Player player);

	/**
	 * @param requester
	 * @param player
	 */
	void requestOpponent(Player requester, Player player);

	/**
	 * @param requester
	 * @param player
	 */
	void registerOpponent(Player requester, Player player);

	/**
	 * @param player
	 * @return
	 */
	List<String> checkRequest(Player player);

	/**
	 * @param player
	 * @param opponent
	 */
	void deletePlayerRequest(Player player, Player opponent);

	/**
	 * @param player
	 * @param opponent
	 * @return
	 */
	boolean checkRequestAcceptance(Player player, Player opponent);

	/**
	 * @param player
	 */
	void deletePlayerOpponentRecords(Player player);

	/**
	 * @param player
	 */
	void deletePlayerRequests(Player player);

	void updateScore(Player player);

}