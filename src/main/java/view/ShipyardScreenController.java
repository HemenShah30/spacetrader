package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.controlsfx.dialog.Dialogs;

import controller.GameEngine;
import model.Player;
import model.Enum.ShipType;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Controller for the Shipyard screen
 * 
 * @author Hemen Shah
 *
 */
public class ShipyardScreenController {

	@FXML
	private Label playerShipNameLbl;

	@FXML
	private Label shipyardShipShieldSlotsLbl;

	@FXML
	private Label playerNameLbl;

	@FXML
	private Label shipyardShipNameLbl;

	@FXML
	private Label planetResourceLbl;

	@FXML
	private Label shipyardShipHPLbl;

	@FXML
	private Label shipyardShipGadgetSlotsLbl;

	@FXML
	private Label planetNameLbl;

	@FXML
	private Label planetTechLevelLbl;

	@FXML
	private Label playerShipFuelLbl;

	@FXML
	private Button buyShipBtn;

	@FXML
	private Label playerShipShieldSlotsLbl;

	@FXML
	private Label playerShipGadgetSlotsLbl;

	@FXML
	private Label shipyardShipCargoSizeLbl;

	@FXML
	private Label playerCreditsLbl;

	@FXML
	private Label playerShipWeaponSlotsLbl;

	@FXML
	private Label shipyardShipFuelLbl;

	@FXML
	private Label playerShipCargoSizeLbl;

	@FXML
	private Label shipyardShipWeaponSlotsLbl;

	@FXML
	private Label shipyardShipCrewSpaceLbl;

	@FXML
	private Label playerShipCrewSpaceLbl;

	@FXML
	private ComboBox<ShipType> shipDropDown;

	@FXML
	private Label playerShipHPLbl;

	@FXML
	private Button backToPlanetBtn;

	/**
	 * Method for selecting a new ship from the drop down menu
	 * 
	 * @param event
	 *            The event that fired the method
	 */
	@FXML
	private void selectShip(Event event) {
		updateShipLabels();
	}

	/**
	 * Method for buying a ship from the shipyard
	 * 
	 * @param event
	 *            The event that fired the method
	 */
	@FXML
	private void buyShipFromShipyard(Event event) {
		if (MultiPageController.isValidAction(event)) {
			ShipType ship = shipDropDown.getValue();
			List<String> errors = GameEngine.getGameEngine().tradeWithShipyard(
					ship);
			if (errors.size() == 0) {
				initializePage();
			} else {
				String errorMsg = "";
				for (String error : errors)
					errorMsg += error + "\n";
				displayError(errorMsg);
			}
		}
	}

	/**
	 * Sends player back to the planet screen from the market screen
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	private void toPlanetScreen(Event e) {
		if (MultiPageController.isValidAction(e)) {
			try {
				Stage stage = (Stage) backToPlanetBtn.getScene().getWindow();
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

	/**
	 * Method for initializing the page with starting information
	 */
	public void initializePage() {
		GameEngine game = GameEngine.getGameEngine();
		Player player = game.getPlayer();
		List<ShipType> shipTypes = new ArrayList<ShipType>();
		for (ShipType st : ShipType.values()) {
			if (!st.equals(player.getShip().getShipType()))
				shipTypes.add(st);
		}
		shipDropDown.getItems().removeAll(ShipType.values());
		shipDropDown.getItems().addAll(shipTypes);
		shipDropDown.setConverter(new StringConverter<ShipType>() {

			@Override
			public String toString(ShipType ship) {
				if (ship == null)
					return null;
				return ship.toString();
			}

			@Override
			public ShipType fromString(String string) {
				return ShipType.valueOf(string.toUpperCase());
			}
		});
		shipDropDown.setValue(shipTypes.get(0));
		planetNameLbl.setText(player.getPlanet().getName());
		planetTechLevelLbl.setText("Tech Level: "
				+ player.getPlanet().getTechLevel().toString());
		planetResourceLbl.setText("Resource: "
				+ player.getPlanet().getResource().toString());
		playerNameLbl.setText(player.getName());
		playerCreditsLbl.setText("Credits: " + player.getCredits());
		updateShipLabels();
	}

	/**
	 * Updates the various ship labels
	 */
	private void updateShipLabels() {
		if (shipDropDown.getItems().size() > 0) {
			GameEngine game = GameEngine.getGameEngine();
			ShipType playerShipType = game.getPlayer().getShip().getShipType();
			ShipType selectedShipType = shipDropDown.getValue();
			playerShipNameLbl.setText(playerShipType.toString());
			playerShipFuelLbl
					.setText(Integer.toString(playerShipType.getFuel()));
			playerShipHPLbl.setText(Integer.toString(playerShipType
					.getTotalHP()));
			playerShipCargoSizeLbl.setText(Integer.toString(playerShipType
					.getCargoSize()));
			playerShipWeaponSlotsLbl.setText(Integer.toString(playerShipType
					.getWeaponSlots()));
			playerShipShieldSlotsLbl.setText(Integer.toString(playerShipType
					.getShieldSlots()));
			playerShipGadgetSlotsLbl.setText(Integer.toString(playerShipType
					.getGadgetSlots()));
			playerShipCrewSpaceLbl.setText(Integer.toString(playerShipType
					.getCrewSpace()));
			shipyardShipNameLbl.setText(selectedShipType.toString());
			shipyardShipFuelLbl.setText(Integer.toString(selectedShipType
					.getFuel()));
			shipyardShipHPLbl.setText(Integer.toString(selectedShipType
					.getTotalHP()));
			shipyardShipCargoSizeLbl.setText(Integer.toString(selectedShipType
					.getCargoSize()));
			shipyardShipWeaponSlotsLbl.setText(Integer
					.toString(selectedShipType.getWeaponSlots()));
			shipyardShipShieldSlotsLbl.setText(Integer
					.toString(selectedShipType.getShieldSlots()));
			shipyardShipGadgetSlotsLbl.setText(Integer
					.toString(selectedShipType.getGadgetSlots()));
			shipyardShipCrewSpaceLbl.setText(Integer.toString(selectedShipType
					.getCrewSpace()));
			buyShipBtn.setText("Buy For "
					+ (int) (selectedShipType.getPrice() - game
							.getPlayerAssetValue()) + " Credits");
		}
	}

	/**
	 * Creates a dialog error box with the given message
	 * 
	 * @param msg
	 *            The message for the error dialog to display
	 */
	private void displayError(String msg) {
		Dialogs.create().owner(buyShipBtn.getScene().getWindow())
				.title("Error").message(msg).showError();
	}
}