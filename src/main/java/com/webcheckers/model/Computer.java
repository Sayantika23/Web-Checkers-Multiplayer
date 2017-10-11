package com.webcheckers.model;

/**
 * The Class Computer.
 */
public class Computer implements Player {

	/** The username. */
	private String username;
	
	/** The password. */
	private String password;

	/* (non-Javadoc)
	 * @see com.webcheckers.model.Player#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/* (non-Javadoc)
	 * @see com.webcheckers.model.Player#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see com.webcheckers.model.Player#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/* (non-Javadoc)
	 * @see com.webcheckers.model.Player#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
}
