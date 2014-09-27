package view;

import java.io.IOException;

import controller.GameEngine;
import model.GoodType;
import model.Marketplace;
import model.Planet;
import model.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the TradeScreen view
 * 
 * @author Jack Croft
 *
 */
public class TradeScreenController {

	@FXML
	private Label buyPriceGamesLbl;
	@FXML
	private Label sellPriceFirearmsLbl;
	@FXML
	private Button buyWaterBtn;
	@FXML
	private Button sellMedicineBtn;
	@FXML
	private Label buyPriceNarcoticsLbl;
	@FXML
	private Label buyPriceFurLbl;
	@FXML
	private Label marketNarcoticsLbl;
	@FXML
	private Button sellFursBtn;
	@FXML
	private Label sellPriceMachinesLbl;
	@FXML
	private Label sellPriceNarcoticsLbl;
	@FXML
	private Button sellNarcoticsBtn;
	@FXML
	private Label buyPriceRobotsLbl;
	@FXML
	private Label buyPriceFoodLbl;
	@FXML
	private Label buyPriceMachinesLbl;
	@FXML
	private Label sellPriceMedicineLbl;
	@FXML
	private Button sellGamesBtn;
	@FXML
	private Button buyRobotsBtn;
	@FXML
	private Label cargoFurLbl;
	@FXML
	private Label cargoMedicineLbl;
	@FXML
	private Label marketFirearmsLbl;
	@FXML
	private Button sellFirearmsBtn;
	@FXML
	private Label sellPriceWaterLbl;
	@FXML
	private Label cargoWaterLbl;
	@FXML
	private Label buyPriceFirearmsLbl;
	@FXML
	private Label marketFoodLbl;
	@FXML
	private Label marketWaterLbl;
	@FXML
	private Label sellPriceRobotsLbl;
	@FXML
	private Label cargoMachinesLbl;
	@FXML
	private Label sellPriceFoodLbl;
	@FXML
	private Button sellRobotsBtn;
	@FXML
	private Label cargoOreLbl;
	@FXML
	private Button sellMachinesBtn;
	@FXML
	private Label cargoNarcoticsLbl;
	@FXML
	private Button buyFoodBtn;
	@FXML
	private Label cargoRobotsLbl;
	@FXML
	private Label marketGamesLbl;
	@FXML
	private Button sellFoodBtn;
	@FXML
	private Label marketOreLbl;
	@FXML
	private Button buyMedicineBtn;
	@FXML
	private Label buyPriceOreLbl;
	@FXML
	private Label cargoGamesLbl;
	@FXML
	private Button buyFirearmsBtn;
	@FXML
	private Label marketRobotsLbl;
	@FXML
	private Label sellPriceOreLbl;
	@FXML
	private Label buyPriceMedicineLbl;
	@FXML
	private Label buyPriceWaterLbl;
	@FXML
	private Label cargoFoodLbl;
	@FXML
	private Button buyGamesBtn;
	@FXML
	private Label sellPriceGamesLbl;
	@FXML
	private Button sellWaterBtn;
	@FXML
	private Button sellOreBtn;
	@FXML
	private Button buyFursBtn;
	@FXML
	private Label marketFurLbl;
	@FXML
	private Button buyOreBtn;
	@FXML
	private Button buyNarcoticsBtn;
	@FXML
	private Label cargoFirearmsLbl;
	@FXML
	private Label sellPriceFurLbl;
	@FXML
	private Label marketMedicineLbl;
	@FXML
	private Label marketMachinesLbl;
	@FXML
	private Button buyMachinesBtn;
	@FXML
	private Label planetNameLbl;
	@FXML
	private Label techLevelLbl;
	@FXML
	private Label governmentLbl;
	@FXML
	private Label playerNameLbl;
	@FXML
	private Label numCreditsLbl;
	@FXML
	private Label cargoSpaceLbl;
	@FXML
	private Button toPlanetScreenBtn;

	/**
	 * Handles all the buy buttons in the trade scene
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void buyGood(Event e) {
		if (MultiPageController.isValidAction(e)) {
			Button btn = (Button) e.getSource();
			String id = btn.getId();
			GoodType type;
			switch (id) {
			case "buyWaterBtn":
				type = GoodType.WATER;
				break;
			case "buyFursBtn":
				type = GoodType.FURS;
				break;
			case "buyFoodBtn":
				type = GoodType.FOOD;
				break;
			case "buyOreBtn":
				type = GoodType.ORE;
				break;
			case "buyGamesBtn":
				type = GoodType.GAMES;
				break;
			case "buyMedicineBtn":
				type = GoodType.MEDICINE;
				break;
			case "buyFirearmsBtn":
				type = GoodType.FIREARMS;
				break;
			case "buyMachinesBtn":
				type = GoodType.MACHINES;
				break;
			case "buyNarcoticsBtn":
				type = GoodType.NARCOTICS;
				break;
			case "buyRobotsBtn":
				type = GoodType.ROBOTS;
				break;
			default:
				throw new IllegalArgumentException();
			}

			try {
				Stage buyPopup = new Stage();
				buyPopup.initModality(Modality.APPLICATION_MODAL);
				buyPopup.initOwner((Stage) btn.getScene().getWindow());

				FXMLLoader loader = new FXMLLoader(
						ClassLoader.getSystemResource("view/BuyGoodPopup.fxml"));
				Parent newScene = loader.load();
				buyPopup.setScene(new Scene(newScene, 300, 125));

				BuyGoodPopupController controller = loader.getController();
				controller.initializePage(type, this);
				buyPopup.show();

			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Handles all the sell buttons in the trade screen
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void sellGood(Event e) {
		if (MultiPageController.isValidAction(e)) {
			Button btn = (Button) e.getSource();
			String id = btn.getId();
			GoodType type;
			switch (id) {
			case "sellWaterBtn":
				type = GoodType.WATER;
				break;
			case "sellFursBtn":
				type = GoodType.FURS;
				break;
			case "sellFoodBtn":
				type = GoodType.FOOD;
				break;
			case "sellOreBtn":
				type = GoodType.ORE;
				break;
			case "sellGamesBtn":
				type = GoodType.GAMES;
				break;
			case "sellMedicineBtn":
				type = GoodType.MEDICINE;
				break;
			case "sellFirearmsBtn":
				type = GoodType.FIREARMS;
				break;
			case "sellMachinesBtn":
				type = GoodType.MACHINES;
				break;
			case "sellNarcoticsBtn":
				type = GoodType.NARCOTICS;
				break;
			case "sellRobotsBtn":
				type = GoodType.ROBOTS;
				break;
			default:
				throw new IllegalArgumentException();
			}

			try {
				Stage sellPopup = new Stage();
				sellPopup.initModality(Modality.APPLICATION_MODAL);
				sellPopup.initOwner((Stage) btn.getScene().getWindow());

				FXMLLoader loader = new FXMLLoader(
						ClassLoader
								.getSystemResource("view/SellGoodPopup.fxml"));
				Parent newScene = loader.load();
				sellPopup.setScene(new Scene(newScene, 300, 125));

				SellGoodPopupController controller = loader.getController();
				controller.initializePage(type, this);
				sellPopup.show();

			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Updates the page with all new data at initial load and after buy or sell
	 */
	public void updatePage() {
		GameEngine game = GameEngine.getGameEngine();
		Player player = game.getPlayer();
		Planet planet = player.getPlanet();
		planetNameLbl.setText(planet.getName());
		techLevelLbl.setText("Tech Level: " + planet.getTechLevel());
		governmentLbl.setText("Government: " + planet.getGovernment());
		Marketplace market = planet.getMarketplace();
		for (GoodType type : GoodType.values()) {
			switch (type) {
			case WATER:
				marketWaterLbl.setText(Integer.toString(market
						.getQuantity(type)));
				buyPriceWaterLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoWaterLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceWaterLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			case FURS:
				marketFurLbl
						.setText(Integer.toString(market.getQuantity(type)));
				buyPriceFurLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoFurLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceFurLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			case FOOD:
				marketFoodLbl
						.setText(Integer.toString(market.getQuantity(type)));
				buyPriceFoodLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoFoodLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceFoodLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			case ORE:
				marketOreLbl
						.setText(Integer.toString(market.getQuantity(type)));
				buyPriceOreLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoOreLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceOreLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			case GAMES:
				marketGamesLbl.setText(Integer.toString(market
						.getQuantity(type)));
				buyPriceGamesLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoGamesLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceGamesLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			case MEDICINE:
				marketMedicineLbl.setText(Integer.toString(market
						.getQuantity(type)));
				buyPriceMedicineLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoMedicineLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceMedicineLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			case FIREARMS:
				marketFirearmsLbl.setText(Integer.toString(market
						.getQuantity(type)));
				buyPriceFirearmsLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoFirearmsLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceFirearmsLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			case MACHINES:
				marketMachinesLbl.setText(Integer.toString(market
						.getQuantity(type)));
				buyPriceMachinesLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoMachinesLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceMachinesLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			case NARCOTICS:
				marketNarcoticsLbl.setText(Integer.toString(market
						.getQuantity(type)));
				buyPriceNarcoticsLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoNarcoticsLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceNarcoticsLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			case ROBOTS:
				marketRobotsLbl.setText(Integer.toString(market
						.getQuantity(type)));
				buyPriceRobotsLbl.setText(Integer.toString((int) market
						.getBuyPrice(type, player)));
				cargoRobotsLbl.setText(Integer.toString(player.getShip()
						.amountInCargo(type)));
				sellPriceRobotsLbl.setText(Integer.toString((int) market
						.getSellPrice(type)));
				break;
			default:
			}
		}
		playerNameLbl.setText(player.getName());
		numCreditsLbl.setText("Credits: " + (int) (player.getCredits()));
		cargoSpaceLbl.setText("Cargo Space: " + player.getShip().getCurrCargo()
				+ "/" + player.getShip().getCargoSize());
	}

	/**
	 * Sends player back to the planet screen from the market screen
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void toPlanetScreen(Event e) {
		if (MultiPageController.isValidAction(e)) {
			MultiPageController.loadView(toPlanetScreenBtn, "PlanetScreen");
		}
	}
}