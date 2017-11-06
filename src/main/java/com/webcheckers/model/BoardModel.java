package com.webcheckers.model;

import java.lang.reflect.Array;

import com.webcheckers.ui.JsonUtils;

public class BoardModel {
	private int[][] board;
	
	public BoardModel() {
		
	}
	
	public Array setBoardModel(String json) {
		return JsonUtils.fromJson(json, Array.class);
	}
	
	public int[][] getBoardModel() {
		return board;
	}
}
