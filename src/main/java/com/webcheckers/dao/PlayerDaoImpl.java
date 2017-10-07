package com.webcheckers.dao;

import com.webcheckers.model.Human;
import com.webcheckers.model.Player;

public class PlayerDaoImpl implements PlayerDao {

	public PlayerDaoImpl() {

	}

	@Override
	public void savePlayer(Player player) {
		// TODO write Spark persistence logic here
	}

	@Override
	public Player findPlayerByUsername(String username) {
		// TODO write Spark persistence logic here
		Human player = new Human();
		if (username.equals("kk3671")) {
			player.setUsername("kk3671");
		} else {
			player = null;
		}
		return player;
	}

	@Override
	public String getPassword(String username) {
		// TODO write Spark persistence logic here
		if (username.equals("kk3671")) {
			return "kishan";
		}
		return "";
	}
}
