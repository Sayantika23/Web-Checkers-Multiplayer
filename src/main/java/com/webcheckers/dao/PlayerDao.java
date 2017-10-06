package com.webcheckers.dao;

import com.webcheckers.model.Player;

public interface PlayerDao {
	void savePlayer(Player player);
	Player findPlayerByUsername(String username);
	String getPassword(String username);

}