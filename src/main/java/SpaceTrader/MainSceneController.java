package SpaceTrader;

import java.io.IOException;

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
		try {
			Parent charCreateScene = FXMLLoader.load(getClass().getResource(
					"CharacterCreator.fxml"));
			Stage stage = (Stage) newGame.getScene().getWindow();
			stage.setScene(new Scene(charCreateScene, 600, 500));
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	@FXML
	private void loadGame(Event e) {

	}

	@FXML
	private void openSettingsMenu(Event e) {

	}
}