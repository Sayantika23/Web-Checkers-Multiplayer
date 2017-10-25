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
		menu = new Menu();
	}
	
	// buttons
	
	/**
	 * Gets the home signup button.
	 *
	 * @return the home signup button
	 */
	public Button getHomeSignupButton() {
		button = new Button();
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
		button = new Button();
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
		button = new Button();
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
		button = new Button();
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Pause");
		return button;
	}

	/**
	 *
	 * @return a select button
	 */
	public Button getSelectButton() {
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Select");
		return button;
	}

	/**
	 *
	 * @return a button with computer as text
	 */
	public Button getComputerPlayerSelectionButton() {
		button = new Button();
		button.setButtonClass("btn btn-lg btn-info btn-block");
		button.setButtonType("submit");
		button.setButtonText("Computer");
		return button;
	}

	/**
	 *
	 * @return a button with human as text
	 */
	public Button getHumanPlayerSelectionButton() {
		button = new Button();
		button.setButtonClass("btn btn-lg btn-warning btn-block");
		button.setButtonType("submit");
		button.setButtonText("Human");
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
