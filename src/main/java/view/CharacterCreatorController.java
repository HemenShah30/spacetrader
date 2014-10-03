package view;

import java.io.IOException;

import org.controlsfx.dialog.Dialogs;

import controller.GameEngine;
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
 * 
 * @author Hemen Shah
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
		if (MultiPageController.isValidAction(e)) {
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
				Dialogs.create()
						.owner(newGame.getScene().getWindow())
						.title("Error")
						.message(
								"You must allocate all your skill points for your character")
						.showError();
			} else {
				GameEngine game = GameEngine.getGameEngine();
				game.setPlayer(playerName, (int) pilotSlider.getValue(),
						(int) fightingSlider.getValue(),
						(int) traderSlider.getValue(),
						(int) engineerSlider.getValue(),
						(int) investorSlider.getValue());

				try {
					Stage stage = (Stage) newGame.getScene().getWindow();
					stage.hide();
					FXMLLoader loader = new FXMLLoader(
							ClassLoader
									.getSystemResource("view/PlanetScreen.fxml"));
					Parent newScene = loader.load();
					stage.setScene(new Scene(newScene, 600, 400));
					PlanetScreenController controller = loader.getController();
					controller.initializePage();
					stage.show();
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
		if (MultiPageController.isValidAction(e)) {
			MultiPageController.loadView(back, "MainScene");
		}
	}
}