package com.webcheckers.dao;

import com.webcheckers.model.Player;

public interface PlayerDao {
	Player findPlayerByUsername(String username);
	void savePlayer(Player player);
	boolean passwordsMatch(Player player);
}