package SpaceTrader;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
		boolean shouldStart = true;
		if (e.getEventType().getName() == "KEY_PRESSED"
				&& ((KeyEvent) e).getCode() != KeyCode.ENTER) {
			shouldStart = false;
		}

		if (shouldStart) {
			try {
				Parent charCreateScene = FXMLLoader.load(getClass()
						.getResource("CharacterCreator.fxml"));
				Stage stage = (Stage) newGame.getScene().getWindow();
				stage.setScene(new Scene(charCreateScene, 600, 500));
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	@FXML
	/**
	 * Method for loading a previously saved game
	 * @param e The event that fired the method
	 */
	protected void loadGame(Event e) {
		boolean shouldLoad = true;
		if (e.getEventType().getName() == "KEY_PRESSED"
				&& ((KeyEvent) e).getCode() != KeyCode.ENTER) {
			shouldLoad = false;
		}

		if (shouldLoad) {
			System.out.println("Load Game");
		}
	}

	@FXML
	/**
	 * Method for opening the settings menu
	 * @param e The event that fired the method
	 */
	protected void openSettingsMenu(Event e) {
		boolean shouldOpen = true;
		if (e.getEventType().getName() == "KEY_PRESSED"
				&& ((KeyEvent) e).getCode() != KeyCode.ENTER) {
			shouldOpen = false;
		}

		if (shouldOpen) {
			System.out.println("Open Settings");
		}
	}
}