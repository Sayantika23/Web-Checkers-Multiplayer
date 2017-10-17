package com.webcheckers.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webcheckers.model.Human;
import com.webcheckers.model.Player;
import com.webcheckers.ui.JsonUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerDaoImpl.
 */
public class PlayerDaoImpl implements PlayerDao {

	/** The player file location. */
	private final String PLAYER_FILE_LOCATION = "database/players.txt";
	/** The player status file location. */
	private final String PLAYER_STATUS_FILE_LOCATION = "database/player_status.txt";

	/**
	 * Instantiates a new player dao impl.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PlayerDaoImpl() throws IOException {
		File file = new File(PLAYER_FILE_LOCATION);
		file.createNewFile();
	}

	/* (non-Javadoc)
	 * @see com.webcheckers.dao.PlayerDao#savePlayer(com.webcheckers.model.Player)
	 */
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

	/* (non-Javadoc)
	 * @see com.webcheckers.dao.PlayerDao#findPlayerByUsername(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see com.webcheckers.dao.PlayerDao#passwordsMatch(com.webcheckers.model.Player)
	 */
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

	/* (non-Javadoc)
	 * @see com.webcheckers.dao.PlayerDao#savePlayer(com.webcheckers.model.Player)
	 */
	@Override
	public void savePlayerStatus(Player player, boolean status) {
		try {
			JsonObject attributesObject = new JsonObject();
			attributesObject.addProperty("username", player.getUsername());
			attributesObject.addProperty("status", status);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(PLAYER_STATUS_FILE_LOCATION, true));
			outputStream.write(JsonUtils.toJson(attributesObject));
			outputStream.newLine();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<String> getPlayersQueue(){
		List<String> players = new ArrayList<>();
		JsonElement parserObject;
		String fileName = PLAYER_STATUS_FILE_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject();
				JsonElement playerName = playerObject.get("username");
				JsonElement playerStatus = playerObject.get("status");
				if(Boolean.valueOf(playerStatus.toString())){
					players.add(playerName.toString());
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return players;
	}
}
