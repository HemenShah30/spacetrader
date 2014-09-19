package view;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Controller for the StartScreen scene
 * 
 * @author Hemen Shah
 */
public class StartScreenController {
	@FXML
	Button beginButton;

	/**
	 * Starts the new game by creating the character
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void begin(Event e) {
		boolean shouldGoBack = true;
		if (e.getEventType().getName() == "KEY_PRESSED"
				&& ((KeyEvent) e).getCode() != KeyCode.ENTER) {
			shouldGoBack = false;
		}

		if (shouldGoBack) {
			try {
				Parent charCreateScene = FXMLLoader.load(getClass()
						.getResource("../view/MainScene.fxml"));
				Stage stage = (Stage) beginButton.getScene().getWindow();
				stage.setScene(new Scene(charCreateScene, 600, 400));
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}
}