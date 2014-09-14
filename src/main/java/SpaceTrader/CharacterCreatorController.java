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
	 * Validates the total amount of skill points allocated and updates the
	 * amount of skill points left
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void validateAndUpdateSkillPoints(Event e) {
		double totalPoints = pilot.getValue() + fight.getValue()
				+ invest.getValue() + engineer.getValue() + trade.getValue();
		if (totalPoints > 15) {
			Slider slider = ((Slider) e.getSource());
			double points = 15 - (totalPoints - slider.getValue());
			slider.setValue(points);
			totalPoints = 15;
		}

		pointsLeft.setText("Points Left: " + (int) (15 - totalPoints));
	}

	/**
	 * Creates new player and game
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void startNewGame(Event e) {
		String playerName = name.getText().trim();
		double totalPoints = pilot.getValue() + fight.getValue()
				+ invest.getValue() + engineer.getValue() + trade.getValue();

		if (playerName.equals("")) {
			// fire some sort of alert at the user about user name
		} else if (totalPoints != 15) {
			// fire some sort of alert at the user about unallocated points
		} else {
			Player p = new Player(playerName, (int) pilot.getValue(),
					(int) fight.getValue(), (int) trade.getValue(),
					(int) engineer.getValue(), (int) invest.getValue());
			try {
				Parent charCreateScene = FXMLLoader.load(getClass()
						.getResource("MainScene.fxml"));
				Stage stage = (Stage) newGame.getScene().getWindow();
				stage.setScene(new Scene(charCreateScene, 600, 500));
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Returns the game to the main menu
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void backToMainMenu(Event e) {
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