package com.webcheckers.controller;

import com.webcheckers.model.Button;
import com.webcheckers.model.Game;
import com.webcheckers.model.Menu;

public class GUIController {
	
	private Button homeButton;

	public GUIController(Game game) {
		
	}
	
	// buttons
	
	public Button getHomeSignupButton() {
		Button button = new Button();
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Submit");
		return button;
	}
	
	public Button getPauseButton() {
		Button button = new Button();
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Pause");
		return button;
	}
	
	// menus
	
	public Menu getGameMenu() {
		Menu menu = new Menu();
		menu.setPlayerOneScore("0");
		menu.setPlayerTwoScore("0");
		return menu;
	}

}
