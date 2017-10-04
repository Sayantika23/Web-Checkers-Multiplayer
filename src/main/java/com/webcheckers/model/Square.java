package com.webcheckers.model;

import java.util.ArrayList;

import javax.swing.text.Position;

public class Square implements GUI {
	
	private int index;
	private ArrayList<Checker> iterator;
	
	public Square(int index) {
		this.index = index;
		iterator = new ArrayList<Checker>();
	}
	
	public ArrayList<Checker> iterator() {
		for (Integer i = 0; i < 64; i++) {
			Checker checker = new Checker(i);
			iterator.add(checker);
		}
		return iterator;
	}
	
	public int getIndex() {
		return index;
	}

	@Override
	public void draw(Position position) {
		// TODO Auto-generated method stub
		
	}

}
