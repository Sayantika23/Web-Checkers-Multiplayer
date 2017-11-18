package com.webcheckers.model;

import org.eclipse.jetty.websocket.api.*;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.webcheckers.controller.GamePlayController;
import com.webcheckers.ui.JsonUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * The Class EchoWebSocket.
 */
@WebSocket
public class EchoWebSocket {

	private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();
	private Board board;

	/**
	 * Connected.
	 *
	 * @param session the session
	 */
	@OnWebSocketConnect
	public void connected(Session session) {
		sessions.add(session);
		board = new GamePlayController().getBoard();
	}

	/**
	 * Closed.
	 *
	 * @param session the session
	 * @param statusCode the status code
	 * @param reason the reason
	 */
	@OnWebSocketClose
	public void closed(Session session, int statusCode, String reason) {
		sessions.remove(session);
	}

	/**
	 * Message.
	 * 
	 * Checks if player list size
	 * and notifies connected client
	 * 
	 * If the player list contains
	 * only one user then the client
	 * messages all sessions
	 * 
	 * If the player list contains
	 * more than one user then the
	 * client only messages the
	 * opponent's session
	 *
	 * @param session the session
	 * @param message the message
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@OnWebSocketMessage
	public void message(Session session, String message) throws IOException {
		for (Session sess : sessions) {
			try {
				sess.getRemote().sendString(parseJsonMessage(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Parses the json message.
	 *
	 * @param json the json
	 * @return the string
	 */
	public String parseJsonMessage(String json) {
		JsonArray jsonArray = JsonUtils.fromJson(json, JsonArray.class);
		JsonElement originalPosition = jsonArray.get(0);
		JsonArray array1 = originalPosition.getAsJsonArray();
		String vector1 = array1.get(0).getAsString();
		String color1 = array1.get(1).getAsString();

		int color = 0;
		switch (color1) {
		case "RED":
			color = 1;
			break;
		case "BLACK":
			color = 3;
			break;
		}

		String[] vectors1 = vector1.split(",");
		int currRow = Integer.parseInt(vectors1[0]);
		int currCol = Integer.parseInt(vectors1[1]);

		JsonElement newPosition = jsonArray.get(1);
		JsonArray array2 = newPosition.getAsJsonArray();
		String vector2 = array2.get(0).getAsString();

		String[] vectors2 = vector2.split(",");
		int moveRow = Integer.parseInt(vectors2[0]);
		int moveCol = Integer.parseInt(vectors2[1]);

		Move move = new Move(currRow, currCol, moveRow, moveCol);
		board.setPlayer(color);

		boolean validMove = board.isValidMove(move);

		if (validMove) {
			board.movePiece(move);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("valid", validMove);

		return JsonUtils.toJson(jsonObject);
	}
	
	/**
	 * Web socket handler reconnects intermittently
	 * in case the user refreshes the web page
	 */
	WebSocketHandler wsHandler = new WebSocketHandler() {
		@Override
		public void configure(WebSocketServletFactory factory) {
			factory.getPolicy().setIdleTimeout(1500);
			factory.register(EchoWebSocket.class);
		}
	};

}