package SpaceTrader;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the StartScreen scene
 */
public class StartScreenController {
	@FXML Button beginButton;
	
	/*
	 * Transition to next screen 
	 */
	@FXML private void begin(Event e) {
		// testing code
		beginButton.setText("something happened");
	}
}