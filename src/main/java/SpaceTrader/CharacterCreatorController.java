package SpaceTrader;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
	private void validateAndUpdateSkillPoints(Event e) {
		System.out.println("CHECKED");
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
	private void backToMainMenu(Event e) {
		try {
			Parent charCreateScene = FXMLLoader.load(getClass().getResource(
					"MainScene.fxml"));
			Stage stage = (Stage) back.getScene().getWindow();
			stage.setScene(new Scene(charCreateScene, 600, 500));
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}