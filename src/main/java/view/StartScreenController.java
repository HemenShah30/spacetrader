package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
		if (MultiPageController.isValidAction(e)) {
			MultiPageController.loadView(beginButton, "MainScene");
		}
	}
}