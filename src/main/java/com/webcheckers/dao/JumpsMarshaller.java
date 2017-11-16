package com.webcheckers.dao;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.webcheckers.model.Move;

/**
 * The Class JumpsMarshaller.
 */
public class JumpsMarshaller implements JsonSerializer<Move>, JsonDeserializer<Move> {
	
	/**
	 * Serializes move object and
	 * returns json object
	 */
	@Override
	public JsonElement serialize(Move move, Type type, JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.addProperty("currRow", move.currRow);
		result.addProperty("currCol", move.currCol);
		result.addProperty("moveRow", move.movRow);
		result.addProperty("moveCol", move.movCol);
		return result;
	}

	/**
	 * Deserializes json string
	 * @return move domain object
	 */
	@Override
	public Move deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject result = json.getAsJsonObject();
		int currRow = result.get("currRow").getAsInt();
		int currCol = result.get("currCol").getAsInt();
		int movRow = result.get("movRow").getAsInt();
		int movCol = result.get("movCol").getAsInt();
		Move move = new Move(currRow, currCol, movRow, movCol);
		return move;
	}
}