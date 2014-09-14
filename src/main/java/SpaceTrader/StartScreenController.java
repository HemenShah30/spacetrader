package SpaceTrader;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the StartScreen scene
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
	private void begin(Event e) {
		// testing code
		beginButton.setText("something happened");
	}
}