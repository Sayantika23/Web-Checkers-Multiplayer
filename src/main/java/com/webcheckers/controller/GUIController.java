package com.webcheckers.controller;

import com.webcheckers.model.Button;
import com.webcheckers.model.Game;

public class GUIController {
	
	private Button homeButton;

	public GUIController(Game game) {
		Button button = new Button();
		button.setButtonClass("btn btn-primary");
		setHomeButton(button);
	}

	private void setHomeButton(Button button) {
		this.homeButton = button;
	}
	private Button getHomeButton() {
		return homeButton;
	}

}
