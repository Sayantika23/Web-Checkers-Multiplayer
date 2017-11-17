package com.webcheckers.model;

/**
 * The Interface Player.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public interface Player {
	String getUsername();
	void setUsername(String username);
	String getPassword();
	void setPassword(String password);
	int getScore();
	void setScore(int score);
	void setColor(String string);
	String getColor();
}