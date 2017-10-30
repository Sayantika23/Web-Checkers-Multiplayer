package com.webcheckers.model;

import java.lang.reflect.Array;

import com.webcheckers.ui.JsonUtils;

public class BoardModel {
	private int[][] board;
	
//	public BoardModel() {
//		this.board = Board.board;
//		for (int i = 0; i < board.length; i++) {			
//			for (int j = 0; j < board[i].length; j++) {
//				System.out.println("BOARD: " + board[i][j]);
//			}
//		}
//	}
	
	public Array setBoardModel(String json) {
		return JsonUtils.fromJson(json, Array.class);
	}
	
	public int[][] getBoardModel() {
		return board;
	}
}
