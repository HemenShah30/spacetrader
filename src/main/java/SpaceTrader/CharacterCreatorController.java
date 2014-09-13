package SpaceTrader;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.event.Event;
/**
 * 
 * Controller for the CharacterCreator scene
 * @author user
 *
 */
public class CharacterCreatorController {
	@FXML Slider pilot;
	@FXML Slider fight;
	@FXML Slider invest;
	@FXML Slider engineer;
	@FXML Slider trade;
	@FXML Label pointsLeft;
	@FXML TextField name;
	@FXML Button back;
	@FXML Button newGame;
	
	/*
	 * checks total skill points left
	 */
	@FXML private void check(Event e) {
		
	}
	/*
	 * creates new game (player as well)
	 */
	@FXML private void newGame(Event e) {
		
	}
	/*
	 * goes back
	 */
	@FXML private void back(Event e) {
		
	}
}
