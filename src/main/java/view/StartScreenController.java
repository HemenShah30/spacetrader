package view;

import controller.GameEngine;

import org.controlsfx.dialog.Dialogs;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.File;
import java.util.Optional;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * Controller for the StartScreen scene
 * 
 * @author Hemen Shah
 */
public class StartScreenController {
    @FXML
    Button beginButton;

    AudioStream backgroundMusic;
    AudioData musicData;
    AudioPlayer musicPlayer = AudioPlayer.player;
    /**
     * Starts the new game by creating the character
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void begin(Event event) {
    	MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/test.wav").toURI().toString()));
    	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    	mediaPlayer.play();
        if (MultiPageController.isValidAction(event)) {
            Optional<String> response = Dialogs.create().owner(beginButton.getScene().getWindow())
                    .title("User").message("Please input your username").showTextInput();
            if (response.isPresent() && !response.get().equals("")) {
                String user = response.get();
                GameEngine game = GameEngine.getGameEngine();
                game.createDatabase(user);
                MultiPageController.loadView(beginButton, "MainScene");
            }
        }
    }
}