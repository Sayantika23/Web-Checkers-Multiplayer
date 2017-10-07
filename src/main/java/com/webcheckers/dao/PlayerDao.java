package com.webcheckers.dao;

import com.webcheckers.model.Player;

public interface PlayerDao {
	Player findPlayerByUsername(String username);
	String getPassword(String password);
	void savePlayer(Player player);
}