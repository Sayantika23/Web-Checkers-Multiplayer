package com.webcheckers.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.ui.JsonUtils;

public class PlayerDaoImpl implements PlayerDao {

	private final String JSON_FILE_LOCATION = "database/player.json";
	private JsonObject playersJsonObject;
	
	public PlayerDaoImpl() throws IOException {
		FileWriter fileWriter = new FileWriter(JSON_FILE_LOCATION);
		JsonObject innerObject = new JsonObject();
		innerObject.addProperty("admin", "admin");
		playersJsonObject = new JsonObject();
		playersJsonObject.add("players", innerObject);
		fileWriter.write(JsonUtils.toJson(playersJsonObject));
		fileWriter.close();
	}

	@Override
	public void savePlayer(Player player) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(JSON_FILE_LOCATION));
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = parser.parse(bufferedReader).getAsJsonObject();
		    JsonObject playersObject = jsonObject.getAsJsonObject("players");
		    playersObject.addProperty(player.getUsername(), player.getPassword());
		    BufferedWriter outputStream = new BufferedWriter(new FileWriter(JSON_FILE_LOCATION, false));
		    outputStream.write(JsonUtils.toJson(jsonObject));
		    outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Player findPlayerByUsername(String username) {
		Human player = null;
		Human existingPlayer = null;
		JsonObject obj;
		String fileName = JSON_FILE_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				obj = (JsonObject) new JsonParser().parse(line);
				player = (Human) JsonUtils.fromPlayerJson(obj.toString(), Human.class);
				if (player.getUsername().equals(username)) {
					existingPlayer = player;
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return existingPlayer;
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
