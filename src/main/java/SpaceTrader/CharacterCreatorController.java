package SpaceTrader;

import java.io.IOException;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.event.Event;

/**
 * Controller for the CharacterCreator scene
 */
public class CharacterCreatorController {
	@FXML
	Slider pilotSlider;
	@FXML
	Slider fightingSlider;
	@FXML
	Slider investorSlider;
	@FXML
	Slider engineerSlider;
	@FXML
	Slider traderSlider;
	@FXML
	Label pointsLeft;
	@FXML
	TextField name;
	@FXML
	Button back;
	@FXML
	Button newGame;
	private int totalSkills = 20;

	/**
	 * Validates the total amount of skill points allocated and updates the
	 * amount of skill points left
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void validateAndUpdateSkillPoints(Event e) {
		double totalPoints = pilotSlider.getValue() + fightingSlider.getValue()
				+ investorSlider.getValue() + engineerSlider.getValue()
				+ traderSlider.getValue();
		if (totalPoints > totalSkills) {
			Slider slider = ((Slider) e.getSource());
			double points = totalSkills - (totalPoints - slider.getValue());
			slider.setValue(points);
			totalPoints = totalSkills;
		}

		pointsLeft.setText("Points Left: " + (int) (totalSkills - totalPoints));
	}

	/**
	 * Creates new player and game
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void startNewGame(Event e) {
		boolean shouldStart = true;
		if (e.getEventType().getName() == "KEY_PRESSED"
				&& ((KeyEvent) e).getCode() != KeyCode.ENTER) {
			shouldStart = false;
		}

		if (shouldStart) {
			String playerName = name.getText().trim();
			double totalPoints = pilotSlider.getValue()
					+ fightingSlider.getValue() + investorSlider.getValue()
					+ engineerSlider.getValue() + traderSlider.getValue();

			if (playerName.equals("")) {
				Dialogs.create().owner(newGame.getScene().getWindow())
						.title("Error")
						.message("You must enter a name for your character")
						.showError();

			} else if (totalPoints != totalSkills) {
				Dialogs.create().owner(newGame.getScene().getWindow())
				.title("Error")
				.message("You must allocate all your skill poinst for your character")
				.showError();
			} else {
				Player p = new Player(playerName, (int) pilotSlider.getValue(),
						(int) fightingSlider.getValue(),
						(int) traderSlider.getValue(),
						(int) engineerSlider.getValue(),
						(int) investorSlider.getValue());
				System.out.println(p);
				try {
					Parent charCreateScene = FXMLLoader.load(getClass()
							.getResource("StartScreen.fxml"));
					Stage stage = (Stage) newGame.getScene().getWindow();
					stage.setScene(new Scene(charCreateScene, 600, 500));
				} catch (IOException ie) {
					ie.printStackTrace();
				}
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
		boolean shouldGoBack = true;
		if (e.getEventType().getName() == "KEY_PRESSED"
				&& ((KeyEvent) e).getCode() != KeyCode.ENTER) {
			shouldGoBack = false;
		}

		if (shouldGoBack) {
			try {
				Parent charCreateScene = FXMLLoader.load(getClass()
						.getResource("MainScene.fxml"));
				Stage stage = (Stage) back.getScene().getWindow();
				stage.setScene(new Scene(charCreateScene, 600, 500));
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}
}