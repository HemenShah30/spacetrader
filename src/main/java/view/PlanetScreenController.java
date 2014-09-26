package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the planet screen window
 * 
 * @author Jack Croft
 *
 */
public class PlanetScreenController {
	@FXML
	Button saveGameBtn;

	@FXML
	Button marketplaceBtn;
	
	@FXML
	Button shipyardBtn;
	
	/**
	 * Method for saving the current game data
	 * @param e The event that fired the method
	 */
	@FXML
	protected void saveGame(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Save Game");
		}
	}
	
	/**
	 * Sends the player to the marketplace view
	 * @param e The event that fired the method
	 */
	@FXML
	protected void goToMarketplace(Event e) {
		if (MultiPageController.isValidAction(e)) {
		MultiPageController.loadView(marketplaceBtn, "TradeScreen2");
		}
	}
	
	/**
	 * Sends the player to the shipyard view
	 * @param e The event that fired the method
	 */
	@FXML
	protected void goToShipyard(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Going to shipyard");
		}
	}
}