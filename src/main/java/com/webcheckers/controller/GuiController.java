package com.webcheckers.controller;

import com.webcheckers.model.Button;
import com.webcheckers.model.Menu;

/**
 * The Class GuiController.
 *
 * @author <a href='mailto:epw9195@rit.edu'>Ed Werner</a>
 */
public class GuiController {
	
	/** The button. */
	private Button button;
	
	/** The menu. */
	private Menu menu;

	/**
	 * Instantiates a new gui controller.
	 */
	public GuiController() {
		button = new Button();
		menu = new Menu();
	}
	
	// buttons
	
	/**
	 * Gets the home signup button.
	 *
	 * @return the home signup button
	 */
	public Button getHomeSignupButton() {
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Signup");
		return button;
	}

	/**
	 * Gets the home signin button.
	 *
	 * @return the home signin button
	 */
	public Button getHomeSigninButton() {
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Signin");
		return button;
	}
	
	/**
	 * Gets the game signout button.
	 *
	 * @return the game signout button
	 */
	public Button getGameSignoutButton() {				
		button.setButtonClass("btn btn-warning margin-10");
		button.setButtonType("submit");
		button.setButtonText("Signout");
		return button;
	}

	/**
	 * Gets the pause button.
	 *
	 * @return the pause button
	 */
	public Button getPauseButton() {
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Pause");
		return button;
	}

	public Button getSelectButton() {
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Select");
		return button;
	}
	// menus
	
	/**
	 * Gets the game menu.
	 *
	 * @return the game menu
	 */
	public Menu getGameMenu() {
		menu.setPlayerOneScore(0);
		menu.setPlayerTwoScore(0);
		return menu;
	}
}
