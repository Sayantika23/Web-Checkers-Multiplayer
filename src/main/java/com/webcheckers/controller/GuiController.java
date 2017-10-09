package com.webcheckers.controller;

import javax.swing.text.Position;

import com.webcheckers.model.Button;
import com.webcheckers.model.GUI;
import com.webcheckers.model.Menu;

public class GuiController implements GUI {
	private Button button;
	private Menu menu;

	public GuiController() {
		button = new Button();
		menu = new Menu();
	}

	@Override
	public void draw(Position position) {
		
	}
	
	// buttons
	
	public Button getHomeSignupButton() {
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Signup");
		return button;
	}

	public Button getHomeSigninButton() {
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Signin");
		return button;
	}
	
	public Button getGameSignoutButton() {				
		button.setButtonClass("btn btn-warning margin-10");
		button.setButtonType("submit");
		button.setButtonText("Signout");
		return button;
	}

	public Button getPauseButton() {
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Pause");
		return button;
	}
	
	// menus
	
	public Menu getGameMenu() {
		menu.setPlayerOneScore(0);
		menu.setPlayerTwoScore(0);
		return menu;
	}
}
