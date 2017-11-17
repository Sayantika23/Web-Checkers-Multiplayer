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

/**
 * The Class PlayerDaoImpl.
 */
public class PlayerDaoImpl implements PlayerDao {
	private final String PLAYER_FILE_LOCATION = "database/players.txt";
	private final String PLAYER_STATUS_FILE_LOCATION = "database/player_status.txt";
	private final String PLAYER_OPPONENT_LOCATION = "database/player_opponent.txt";
	private final String PLAYER_REQUEST_LOCATION = "database/player_game_request.txt";
	private final String TEMP_REQUEST_LOCATION = "database/request_temp.txt";

	/**
	 * Instantiates a new player dao impl
	 * and creates new player database files
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public PlayerDaoImpl() throws IOException {
		File playerFile = new File(PLAYER_FILE_LOCATION);
		playerFile.createNewFile();
		File playerStatusFile = new File(PLAYER_STATUS_FILE_LOCATION);
		playerStatusFile.createNewFile();
		File playerOpponentFile = new File(PLAYER_OPPONENT_LOCATION);
		playerOpponentFile.createNewFile();
		File playerRequestFile = new File(PLAYER_REQUEST_LOCATION);
		playerRequestFile.createNewFile();
	}

	/**
	 * Saves new player domain by converting
	 * to json and writing to text file
	 */
	@Override
	public void savePlayer(Player player) {
		try {
			JsonObject playerObject = new JsonObject();
			JsonObject attributesObject = new JsonObject();
			attributesObject.addProperty("username", player.getUsername());
			attributesObject.addProperty("password", player.getPassword());
			attributesObject.addProperty("score", player.getScore());
			playerObject.add("player", attributesObject);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(PLAYER_FILE_LOCATION, true));
			outputStream.write(JsonUtils.toJson(playerObject));
			outputStream.newLine();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Finds player by username string by
	 * parsing text file and deserializing
	 * json string
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

	/**
	 * Verifies if submitted password matches
	 * any registered users by deserialzing
	 * json strings from text file
	 * 
	 * @return boolean
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

	/**
	 * Saves player status by creating
	 * json object and writing json
	 * string to file
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
	 * Gets player queue string list
	 * of player name
	 * @param player
	 * @return player name string list
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
	 * Deletes player status by parsing
	 * textfile and creating json object
	 * and writing player name and status
	 * to file
	 * 
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
	 * Creates player request json
	 * object from requester and 
	 * current player then converts to json
	 * string and writes to text file
	 * 
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

	/**
	 * Checks player request and creates
	 * json objects from text file and
	 * adds player names to string list
	 * 
	 * @param requester
	 * @param player
	 * @return players string list
	 */
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
	 * Registers player and opponent
	 * for checkers game match by
	 * creating json objects and adding
	 * each player username and opponent 
	 * username and writing json string
	 * to text file
	 * 
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
	 * Deleted player request by parsing
	 * reading player request text file
	 * and creating a json object and
	 * writing blank line to text file
	 * 
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
			File tempFileName = new File(TEMP_REQUEST_LOCATION);
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
	 * Checks request acceptance by
	 * reading player opponent text
	 * file and creating json object
	 * and checking if player and
	 * opponent attributes match
	 * 
	 * @param player
	 * @param opponent
	 * @return boolean
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
	 * Deletes player request by reading
	 * player request text file and
	 * creates json object and checks
	 * if request attribute and player
	 * username match then writes a
	 * blank line to text file
	 * 
	 * @param player
	 */
	@Override
	public void deletePlayerRequests(Player player){
		JsonElement parserObject;
		String fileName = PLAYER_REQUEST_LOCATION;
		String line = null;

		try {
			File inputFile = new File(fileName);
			File tempFileName = new File(TEMP_REQUEST_LOCATION);
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
	 * Deletes player opponent record by
	 * reading in player opponent text
	 * file and creating json object and
	 * checking if player attribute matches
	 * player user name and writes an empty
	 * line to text file
	 * 
	 * @param player
	 */
	@Override
	public void deletePlayerOpponentRecords(Player player){
		JsonElement parserObject;
		String fileName = PLAYER_OPPONENT_LOCATION;
		String line = null;

		try {
			File inputFile = new File(fileName);
			File tempFileName = new File(TEMP_REQUEST_LOCATION);
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
	 * Updates player score by reading in
	 * player text file and creating json
	 * object and checking if existing player
	 * username matches player username
	 * then overwrites current line and
	 * writes updated player json string
	 * to text file 
	 * 
	 * @param player
	 */
	@Override
	public void updateScore(Player player) {
		Human existingPlayer = null;
		JsonObject parserObject;
		String fileName = PLAYER_FILE_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(PLAYER_FILE_LOCATION, true));

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = (JsonObject) new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject("player");
				String json = JsonUtils.toJson(playerObject);
				existingPlayer = JsonUtils.fromPlayerJson(json, Human.class);
				if (existingPlayer != null) {
					if (existingPlayer.getUsername().equals(player.getUsername())) {
						String playerJson = JsonUtils.toJson(player);
						outputStream.write(line + System.getProperty("line.separator"));
						outputStream.write(playerJson);
						break;
					}
				}
			}
			outputStream.close();
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
