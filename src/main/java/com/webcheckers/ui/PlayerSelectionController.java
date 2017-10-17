package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.webcheckers.controller.GuiController;
import com.webcheckers.model.Button;
import com.webcheckers.model.Game;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

/**
 * The Web Controller for the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class PlayerSelectionController implements TemplateViewRoute {

    static final String HOME_VIEW_NAME = "playerselection.ftl";
    static final String TITLE = "Web Checkers";
    static final String TITLE_ATTRIBUTE = "title";
    static final String COMPUTER_BUTTON_CLASS = "computerButtonClass";
    static final String COMPUTER_BUTTON_TYPE = "computerButtonType";
    static final String COMPUTER_BUTTON_TEXT = "computerButtonText";
    static final String HUMAN_BUTTON_CLASS = "humanButtonClass";
    static final String HUMAN_BUTTON_TYPE = "humanButtonType";
    static final String HUMAN_BUTTON_TEXT = "humanButtonText";
    static final String LOGIN_STATUS = "loginFail";
    static final String SIGNUP_STATUS = "signupFail";
    static final String LOGIN_MESSAGE = "message";
    static final String LOGIN_PAGE = "signinPage";
    static final String NEW_USER = "newUserSignup";
    static final String SIGNUP_MESSAGE = "SignUpMessage";
    private GuiController guiController;
    private Game game;

    public PlayerSelectionController(Game game) {
        this.game = game;
        Objects.requireNonNull(game, "game must not be null");
        this.guiController = game.getGUIController();
    }

    @Override
    public ModelAndView handle(Request request, Response response) {
        Map<String, Object> vm = new HashMap<>();
        Button humanButton = guiController.getHumanPlayerSelectionButton();
        Button computerButton = guiController.getComputerPlayerSelectionButton();
        vm.put(HUMAN_BUTTON_CLASS, humanButton.getButtonClass());
        vm.put(HUMAN_BUTTON_TYPE, humanButton.getButtonType());
        vm.put(HUMAN_BUTTON_TEXT, humanButton.getButtonText());
        vm.put(COMPUTER_BUTTON_CLASS, computerButton.getButtonClass());
        vm.put(COMPUTER_BUTTON_TYPE, computerButton.getButtonType());
        vm.put(COMPUTER_BUTTON_TEXT, computerButton.getButtonText());
        vm.put(TITLE_ATTRIBUTE, TITLE);
        vm.put(LOGIN_STATUS, false);
        vm.put(SIGNUP_STATUS, false);
        vm.put(LOGIN_MESSAGE, "Welcome");
        vm.put(LOGIN_PAGE, true);
        vm.put(NEW_USER, false);
        vm.put(SIGNUP_MESSAGE, false);
        return new ModelAndView(vm, HOME_VIEW_NAME);
    }
}