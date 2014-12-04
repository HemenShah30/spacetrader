package view;

import controller.GameEngine;

import org.controlsfx.dialog.Dialogs;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Controller for the MainScene
 * 
 * @author Hemen Shah
 * 
 */
public class MainSceneController {
    @FXML
    Button newGame;
    @FXML
    Button loadGame;
    @FXML
    Button settings;

    /**
     * Goes to character creator screen
     * 
     * @param e
     *            Event that fired the method
     */
    @FXML
    private void startNewGame(Event event) {
        if (MultiPageController.isValidAction(event)) {
            MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/character.wav").toURI().toString()));
        	mediaPlayer.setAutoPlay(true);
        	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            MultiPageController.loadView(newGame, "CharacterCreator");
        }
    }

    @FXML
    /**
     * Method for loading a previously saved game
     * @param e The event that fired the method
     */
    private void loadGame(Event event) {
        if (MultiPageController.isValidAction(event)) {
            GameEngine game = GameEngine.getGameEngine();
            if (game.userExists()) {
                try {
                    Optional<String> player = Dialogs.create()
                            .owner(loadGame.getScene().getWindow()).title("Player Select")
                            .message("Please select your character")
                            .showChoices(game.getUserPlayers());

                    if (player.isPresent()) {
                        game.loadGame(player.get());
                        Stage stage = (Stage) loadGame.getScene().getWindow();
                        stage.hide();
                        FXMLLoader loader = new FXMLLoader(
                                ClassLoader.getSystemResource("view/PlanetScreen.fxml"));
                        Parent newScene = loader.load();
                        stage.setScene(new Scene(newScene, 600, 400));
                        PlanetScreenController controller = loader.getController();
                        controller.initializePage();
                        stage.show();
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } else {
                Dialogs.create().owner(loadGame.getScene().getWindow()).title("Error")
                        .message("You do not have a saved game").showError();
            }
        }
    }

    @FXML
    /**
     * Method for opening the settings menu
     * @param e The event that fired the method
     */
    private void openSettingsMenu(Event event) {
        if (MultiPageController.isValidAction(event)) {
            System.out.println("Open Settings");
        }
    }
}