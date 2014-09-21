package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the MainScene
 * 
 * @author Hemen Shah
 * 
 */
public class MainSceneController {
	@FXML
	Button newGame;
	@FXML
	Button loadGame;
	@FXML
	Button settings;

	/**
	 * Goes to character creator screen
	 * 
	 * @param e
	 *            Event that fired the method
	 */
	@FXML
	protected void startNewGame(Event e) {
		if (MultiPageController.isValidAction(e)) {
			MultiPageController.loadView(newGame, "CharacterCreator");
		}
	}

	@FXML
	/**
	 * Method for loading a previously saved game
	 * @param e The event that fired the method
	 */
	protected void loadGame(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Load Game");
		}
	}

	@FXML
	/**
	 * Method for opening the settings menu
	 * @param e The event that fired the method
	 */
	protected void openSettingsMenu(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Open Settings");
		}
	}
}