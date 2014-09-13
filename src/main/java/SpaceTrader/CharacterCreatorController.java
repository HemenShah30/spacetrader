package SpaceTrader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.event.Event;

/**
 * Controller for the CharacterCreator scene
 */
public class CharacterCreatorController {
	@FXML
	Slider pilot;
	@FXML
	Slider fight;
	@FXML
	Slider invest;
	@FXML
	Slider engineer;
	@FXML
	Slider trade;
	@FXML
	Label pointsLeft;
	@FXML
	TextField name;
	@FXML
	Button back;
	@FXML
	Button newGame;

	/**
	 * checks total skill points left
	 * 
	 * @param e
	 *            Event that fired the method
	 */
	@FXML
	private void check(Event e) {

	}

	/**
	 * Creates new player and game
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void startNewGame(Event e) {

	}

	/**
	 * Returns the game to the main menu
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void back(Event e) {

	}
}