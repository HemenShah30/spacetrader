package view;

import java.io.IOException;

import org.controlsfx.dialog.Dialogs;

import controller.GameEngine;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
	private void startNewGame(Event e) {
		if (MultiPageController.isValidAction(e)) {
			MultiPageController.loadView(newGame, "CharacterCreator");
		}
	}

	@FXML
	/**
	 * Method for loading a previously saved game
	 * @param e The event that fired the method
	 */
	private void loadGame(Event e) {
		if (MultiPageController.isValidAction(e)) {
			if (GameEngine.getGameEngine().userExists()) {
				try {
					GameEngine.getGameEngine().loadGame();
					Stage stage = (Stage) loadGame.getScene().getWindow();
					stage.hide();
					FXMLLoader loader = new FXMLLoader(
							ClassLoader
									.getSystemResource("view/PlanetScreen.fxml"));
					Parent newScene = loader.load();
					stage.setScene(new Scene(newScene, 600, 400));
					PlanetScreenController controller = loader.getController();
					controller.initializePage();
					stage.show();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			} else {
				Dialogs.create().owner(loadGame.getScene().getWindow())
						.title("Error").message("You do not have a saved game")
						.showError();
			}
		}
	}

	@FXML
	/**
	 * Method for opening the settings menu
	 * @param e The event that fired the method
	 */
	private void openSettingsMenu(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Open Settings");
		}
	}
}