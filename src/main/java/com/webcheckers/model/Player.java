package com.webcheckers.model;

/**
 * The Interface Player.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public interface Player {
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	String getUsername();
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	void setUsername(String username);
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	String getPassword();
	
	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	void setPassword(String password);

	int getScore();

	void setScore(int score);
}