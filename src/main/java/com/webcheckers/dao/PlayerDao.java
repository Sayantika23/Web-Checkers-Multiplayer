package com.webcheckers.dao;

import com.webcheckers.model.Player;
import java.util.List;

/**
 * The Interface PlayerDao.
 */
public interface PlayerDao {
	Player findPlayerByUsername(String username);
	void savePlayer(Player player);
	boolean passwordsMatch(Player player);
	void savePlayerStatus(Player player, boolean status);
	List<String> getPlayersQueue(Player player);
	void deletePlayerStatus(Player player);
	void requestOpponent(Player requester, Player player);
	void registerOpponent(Player requester, Player player);
	List<String> checkRequest(Player player);
	void deletePlayerRequest(Player player, Player opponent);
	boolean checkRequestAcceptance(Player player, Player opponent);
	void deletePlayerOpponentRecords(Player player);
	void deletePlayerRequests(Player player);
	void updateScore(Player player);
}