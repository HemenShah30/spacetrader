package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the planet screen window
 * 
 * @author Jack Croft
 *
 */
public class PlanetScreenController {
	@FXML
	Button saveGameBtn;

	@FXML
	/**
	 * Method for saving the current game data
	 * @param e The event that fired the method
	 */
	protected void saveGame(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Save Game");
		}
	}
}