package view;

import java.util.Optional;

import org.controlsfx.dialog.Dialogs;

import controller.GameEngine;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the StartScreen scene
 * 
 * @author Hemen Shah
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
    private void begin(Event event) {
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