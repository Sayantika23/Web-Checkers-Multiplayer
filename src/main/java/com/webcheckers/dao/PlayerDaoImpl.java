package com.webcheckers.dao;

import com.webcheckers.model.Player;

public class PlayerDaoImpl implements PlayerDao {
	protected PlayerDaoImpl() {
		
	}

	@Override
	public void savePlayer(Player player) {
		// TODO write Spark persistence logic here
	}

	@Override
	public Player findPlayerByUsername(String username) {
		// TODO write Spark persistence logic here
		Player player = null;
		return player;
	}
}
