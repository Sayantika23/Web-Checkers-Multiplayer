package com.webcheckers.model;

public class Computer implements Player {

	private String username;

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

}
