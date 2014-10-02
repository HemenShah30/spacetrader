package view;

import java.io.IOException;

import model.Planet;
import model.Player;
import controller.GameEngine;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	Button toShipYardBtn;
	
	@FXML
	Button toSpaceBtn;

	@FXML
	Label planetNameLbl;

	@FXML
	Label planetTechLevelLbl;

	@FXML
	Label planetResourceLbl;

	@FXML
	Label playerNameLbl;

	@FXML
	Label playerCreditsLbl;

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
						ClassLoader.getSystemResource("view/TradeScreen.fxml"));
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

	/**
	 * Sends the player to the menu for going to another planet
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void goToSpace(Event e) {
		if (MultiPageController.isValidAction(e)) {
			try {
				Stage stage = (Stage) toSpaceBtn.getScene().getWindow();
				stage.hide();
				FXMLLoader loader = new FXMLLoader(
						ClassLoader.getSystemResource("view/TravelScreen.fxml"));
				Parent newScene = loader.load();
				stage.setScene(new Scene(newScene, 600, 400));
				TravelController controller = loader.getController();
				controller.loadUniverse(null);
				stage.show();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Initializes the main page with the relevant label data
	 */
	public void initializePage() {
		GameEngine game = GameEngine.getGameEngine();
		Player player = game.getPlayer();
		Planet planet = player.getPlanet();
		planetNameLbl.setText(planet.getName());
		planetTechLevelLbl.setText("Tech Level: "
				+ planet.getTechLevel().toString());
		planetResourceLbl.setText("Resource: "
				+ planet.getResource().toString());
		playerNameLbl.setText(player.getName());
		playerCreditsLbl.setText("Credits: " + (int) player.getCredits());
	}
}