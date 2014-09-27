package view;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void saveGame(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Save Game");
		}
	}

	/**
	 * Sends the player to the marketplace view
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void goToMarketplace(Event e) {
		if (MultiPageController.isValidAction(e)) {
			try {
				Stage stage = (Stage) marketplaceBtn.getScene().getWindow();
				stage.hide();
				FXMLLoader loader = new FXMLLoader(
						ClassLoader.getSystemResource("view/TradeScreen2.fxml"));
				Parent newScene = loader.load();
				stage.setScene(new Scene(newScene, 600, 400));
				TradeScreenController controller = loader.getController();
				controller.updatePage();
				stage.show();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Sends the player to the shipyard view
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void goToShipyard(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Going to shipyard");
		}
	}
	
	@FXML
	protected void goToSpace(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Going to space");
		}
	}
}