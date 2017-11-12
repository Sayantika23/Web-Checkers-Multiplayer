package com.webcheckers.dao;

import java.io.*;
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
	/** The player opponent file location. */
	private final String PLAYER_OPPONENT_LOCATION = "database/player_opponent.txt";
	/** The player request file location. */
	private final String PLAYER_REQUEST_LOCATION = "database/player_game_request.txt";

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

	/**
	 * @param player
	 * @return
	 */
	public List<String> getPlayersQueue(Player player){
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
				String playerName = playerObject.get("username").toString().replaceAll("^\"|\"$", "");;
				boolean playerStatus = Boolean.valueOf(playerObject.get("status").toString());
				if(playerStatus & !player.getUsername().equals(playerName)){
					players.add(playerName);
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return players;
	}

	/**
	 * @param player
	 */
	@Override
	public void deletePlayerStatus(Player player){
		JsonElement parserObject;
		String fileName = PLAYER_STATUS_FILE_LOCATION;
		String line = null;

		try {
			File inputFile = new File(fileName);
			File tempFileName = new File("database/temp.txt");
			try {
				tempFileName.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject();
				JsonElement playerName = playerObject.get("username");
				JsonElement playerStatus = playerObject.get("status");
				if (!playerName.toString().replaceAll("^\"|\"$", "").equals(player.getUsername())) {
					writer.write(line + System.getProperty("line.separator"));
				}
			}
			writer.close();
			bufferedReader.close();
			try {
				inputFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				tempFileName.renameTo(inputFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param requester
	 * @param player
	 */
	@Override
	public void requestOpponent(Player requester, Player player){
		try {
			List<String> requesters = checkRequest(player);
			if(!requesters.contains(requester.getUsername())){
				JsonObject attributesObject = new JsonObject();
				attributesObject.addProperty("requestedBy", requester.getUsername());
				attributesObject.addProperty("requestedTo", player.getUsername());
				BufferedWriter outputStream = new BufferedWriter(new FileWriter(PLAYER_REQUEST_LOCATION, true));
				outputStream.write(JsonUtils.toJson(attributesObject));
				outputStream.newLine();
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<String> checkRequest(Player player) {
		List<String> players = new ArrayList<>();
		JsonElement parserObject;
		String fileName = PLAYER_REQUEST_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject();
				String requestedBy = playerObject.get("requestedBy").toString().replaceAll("^\"|\"$", "");;
				String requestedTo = playerObject.get("requestedTo").toString().replaceAll("^\"|\"$", "");;
				if(player.getUsername().equals(requestedTo)){
					players.add(requestedBy);
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return players;
	}

	/**
	 * @param player
	 * @param opponent
	 */
	@Override
	public void registerOpponent(Player player, Player opponent){
		try {
			JsonObject attributesObject = new JsonObject();
			attributesObject.addProperty("player", player.getUsername());
			attributesObject.addProperty("opponent", opponent.getUsername());
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(PLAYER_OPPONENT_LOCATION, true));
			outputStream.write(JsonUtils.toJson(attributesObject));
			outputStream.newLine();
			JsonObject attributesObject1 = new JsonObject();
			attributesObject1.addProperty("player", opponent.getUsername());
			attributesObject1.addProperty("opponent", player.getUsername());
			outputStream.write(JsonUtils.toJson(attributesObject1));
			outputStream.newLine();
			outputStream.close();
			deletePlayerRequest(player, opponent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param player
	 * @param opponent
	 */
	@Override
	public void deletePlayerRequest(Player player, Player opponent){
		JsonElement parserObject;
		String fileName = PLAYER_REQUEST_LOCATION;
		String line = null;

		try {
			File inputFile = new File(fileName);
			File tempFileName = new File("database/request_temp.txt");
			try {
				tempFileName.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject();
				JsonElement playerName = playerObject.get("requestedBy");
				JsonElement OpponentName = playerObject.get("requestedTo");
				boolean samePlayerName = playerName.toString().replaceAll("^\"|\"$", "").equals(opponent.getUsername());
				boolean sameOpponentName = OpponentName.toString().replaceAll("^\"|\"$", "").equals(player.getUsername());
				if (!(samePlayerName & sameOpponentName)) {
					writer.write(line + System.getProperty("line.separator"));
				}
			}
			writer.close();
			bufferedReader.close();
			try {
				inputFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				tempFileName.renameTo(inputFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param player
	 * @param opponent
	 * @return
	 */
	@Override
	public boolean checkRequestAcceptance(Player player, Player opponent){
		List<String> players = new ArrayList<>();
		JsonElement parserObject;
		String fileName = PLAYER_OPPONENT_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject();
				String requestedBy = playerObject.get("player").toString().replaceAll("^\"|\"$", "");;
				String requestedTo = playerObject.get("opponent").toString().replaceAll("^\"|\"$", "");;
				if((player.getUsername().equals(requestedBy) & opponent.getUsername().equals(requestedTo)) |
						(opponent.getUsername().equals(requestedBy) & player.getUsername().equals(requestedTo))){
					return true;
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param player
	 */
	@Override
	public void deletePlayerRequests(Player player){
		JsonElement parserObject;
		String fileName = PLAYER_REQUEST_LOCATION;
		String line = null;

		try {
			File inputFile = new File(fileName);
			File tempFileName = new File("database/request_temp.txt");
			try {
				tempFileName.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject();
				JsonElement playerName = playerObject.get("requestedBy");
				if (!playerName.toString().replaceAll("^\"|\"$", "").equals(player.getUsername())) {
					writer.write(line + System.getProperty("line.separator"));
				}
			}
			writer.close();
			bufferedReader.close();
			try {
				inputFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				tempFileName.renameTo(inputFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param player
	 */
	@Override
	public void deletePlayerOpponentRecords(Player player){
		JsonElement parserObject;
		String fileName = PLAYER_OPPONENT_LOCATION;
		String line = null;

		try {
			File inputFile = new File(fileName);
			File tempFileName = new File("database/request_temp.txt");
			try {
				tempFileName.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName));

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject();
				JsonElement playerName = playerObject.get("player");
				if (!playerName.toString().replaceAll("^\"|\"$", "").equals(player.getUsername())) {
					writer.write(line + System.getProperty("line.separator"));
				}
			}
			writer.close();
			bufferedReader.close();
			try {
				inputFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				tempFileName.renameTo(inputFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param player
	 */
	@Override
	public boolean updateScore(Player player) {
		JsonElement parserObject;
		String fileName = PLAYER_OPPONENT_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = new JsonParser().parse(line);
				String json = JsonUtils.toJson(parserObject);
				Player existingPlayer = JsonUtils.fromPlayerJson(json, Human.class);
				if ((player.getUsername().equals(existingPlayer.getUsername())
						|| player.getPassword().equals(existingPlayer.getPassword()))) {
					player.setScore(existingPlayer.getScore());
					BufferedWriter outputStream = new BufferedWriter(new FileWriter(PLAYER_FILE_LOCATION, true));
					outputStream.write(JsonUtils.toJson(player));
					outputStream.newLine();
					outputStream.close();
					int score = player.getScore();
					player.setScore(score);
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
