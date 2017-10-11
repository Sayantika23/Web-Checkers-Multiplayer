package com.webcheckers.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.ui.JsonUtils;

public class PlayerDaoImpl implements PlayerDao {

	private final String PLAYER_FILE_LOCATION = "database/players.txt";

	public PlayerDaoImpl() throws IOException {
		File file = new File(PLAYER_FILE_LOCATION);
		file.createNewFile();
	}

	@Override
	public void savePlayer(Player player) {
		try {
			JsonObject playerObject = new JsonObject();
			JsonObject attributesObject = new JsonObject();
			attributesObject.addProperty("username", player.getUsername());
			attributesObject.addProperty("password", player.getPassword());
			playerObject.add("player", attributesObject);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(PLAYER_FILE_LOCATION, true));
			outputStream.write(JsonUtils.toJson(playerObject));
			outputStream.newLine();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Player findPlayerByUsername(String username) {
		Human player = null;
		Human existingPlayer = null;
		JsonObject parserObject;
		String fileName = PLAYER_FILE_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = (JsonObject) new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject("player");
				String json = JsonUtils.toJson(playerObject);
				player = JsonUtils.fromPlayerJson(json, Human.class);
				if (player != null) {
					if (player.getUsername().equals(username)) {
						existingPlayer = player;
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return existingPlayer;
	}

	@Override
	public boolean passwordsMatch(Player player) {
		Human existingPlayer = null;
		boolean passwordsMatch = false;
		JsonObject parserObject;
		String line = null;

		try {
			FileReader fileReader = new FileReader(PLAYER_FILE_LOCATION);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			Pattern p = Pattern.compile(Pattern.quote(player.getPassword())); // quotes in case you need 'Hello.'
			int count = 0;
			while ((line = bufferedReader.readLine()) != null)
				for (Matcher m = p.matcher(line); m.find(); count++) {
					parserObject = (JsonObject) new JsonParser().parse(line);
					JsonObject playerObject = parserObject.getAsJsonObject("player");
					String json = JsonUtils.toJson(playerObject);
					existingPlayer = JsonUtils.fromPlayerJson(json, Human.class);
					if (existingPlayer != null) {
						if (existingPlayer.getUsername().equals(player.getUsername())
								&& existingPlayer.getPassword().equals(player.getPassword())) {
							passwordsMatch = true;
						} else {
							passwordsMatch = false;
						}
					}
				}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return passwordsMatch;
	}
}
