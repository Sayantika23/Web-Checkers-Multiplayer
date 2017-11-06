package com.webcheckers.ui;

import spark.ResponseTransformer;
import com.google.gson.Gson;
import com.webcheckers.model.BoardModel;
import com.webcheckers.model.Human;

/**
 * A Utility class for parsing and formatting JSON data.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class JsonUtils {

	/** The Constant GSON. */
	private static final Gson GSON = new Gson();

	/**
	 * From json.
	 *
	 * @param <T> the generic type
	 * @param json the json
	 * @param clazz the clazz
	 * @return the t
	 */
	public static <T> T fromJson(final String json, final Class<T> clazz) {
		return GSON.fromJson(json, clazz);
	}

	/**
	 * To json.
	 *
	 * @param object the object
	 * @return the string
	 */
	public static String toJson(Object object) {
		return GSON.toJson(object);
	}

	/**
	 * Json.
	 *
	 * @return the response transformer
	 */
	public static ResponseTransformer json() {
		return JsonUtils::toJson;
	}

	/**
	 * From player json.
	 *
	 * @param json the json
	 * @param player the player
	 * @return the human
	 */
	public static Human fromPlayerJson(final String json, Class<Human> player) {
		return GSON.fromJson(json, player);
	}

	/**
	 * From player json.
	 *
	 * @param json the json
	 * @param player the player
	 * @return the human
	 */
	public static Human fromPlayerStatusJson(final String json, Class<Human> player) {
		return GSON.fromJson(json, player);
	}
	
	/**
	 * From board model json.
	 *
	 * @param json the json
	 * @param boardModel the board model
	 * @return the board model
	 */
	public static BoardModel fromBoardModelJson(final String json, Class<BoardModel> boardModel) {
		return GSON.fromJson(json, boardModel);
	}
}
