package com.webcheckers.model;

import java.lang.reflect.Array;

import com.webcheckers.ui.JsonUtils;

/**
 * The Class BoardModel.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
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
