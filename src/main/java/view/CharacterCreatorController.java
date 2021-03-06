package view;

import controller.GameEngine;

import org.controlsfx.dialog.Dialogs;

import java.io.IOException;
import java.util.List;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
     * Validates the total amount of skill points allocated and updates the amount of skill points
     * left
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void validateAndUpdateSkillPoints(Event event) {
        double totalPoints = pilotSlider.getValue() + fightingSlider.getValue()
                + investorSlider.getValue() + engineerSlider.getValue() + traderSlider.getValue();
        if (totalPoints > totalSkills) {
            Slider slider = ((Slider) event.getSource());
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
    private void startNewGame(Event event) {
        if (MultiPageController.isValidAction(event)) {
            GameEngine game = GameEngine.getGameEngine();
            List<String> playerNames = game.getUserPlayers();
            for (int i = 0; i < playerNames.size(); i++) {
                String name = playerNames.get(i);
                name = name.toLowerCase();
                playerNames.remove(i);
                playerNames.add(name);
            }
            String playerName = name.getText().trim();
            double totalPoints = pilotSlider.getValue() + fightingSlider.getValue()
                    + investorSlider.getValue() + engineerSlider.getValue()
                    + traderSlider.getValue();

            if (playerName.equals("")) {
                Dialogs.create().owner(newGame.getScene().getWindow()).title("Error")
                        .message("You must enter a name for your character").showError();
            } else if (playerNames.contains(playerName.toLowerCase())) {
                Dialogs.create().owner(newGame.getScene().getWindow()).title("Error")
                        .message("You cannot create two characters with the same name").showError();
            } else if (totalPoints != totalSkills) {

                Dialogs.create().owner(newGame.getScene().getWindow()).title("Error")
                        .message("You must allocate all your skill points for your character")
                        .showError();
            } else {
                game.setPlayer(playerName, (int) pilotSlider.getValue(),
                        (int) fightingSlider.getValue(), (int) traderSlider.getValue(),
                        (int) engineerSlider.getValue(), (int) investorSlider.getValue());

                try {
                    Stage stage = (Stage) newGame.getScene().getWindow();
                    stage.hide();
                    FXMLLoader loader = new FXMLLoader(
                            ClassLoader.getSystemResource("view/PlanetScreen.fxml"));
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
     * @param event
     *            The event that fired the method
     */
    @FXML
    private void backToMainMenu(Event event) {
        if (MultiPageController.isValidAction(event)) {
            MultiPageController.loadView(back, "MainScene");
        }
    }
}