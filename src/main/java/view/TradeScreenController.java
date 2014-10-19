package view;

import java.io.IOException;

import controller.GameEngine;
import model.Marketplace;
import model.Planet;
import model.Player;
import model.Enum.GoodType;
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
	private void buyGood(Event e) {
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
	private void sellGood(Event e) {
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
				setLabels(type, player, market, buyWaterBtn, sellWaterBtn,
						marketWaterLbl, buyPriceWaterLbl, cargoWaterLbl,
						sellPriceWaterLbl);
				break;
			case FURS:
				setLabels(type, player, market, buyFursBtn, sellFursBtn,
						marketFurLbl, buyPriceFurLbl, cargoFurLbl,
						sellPriceFurLbl);
				break;
			case FOOD:
				setLabels(type, player, market, buyFoodBtn, sellFoodBtn,
						marketFoodLbl, buyPriceFoodLbl, cargoFoodLbl,
						sellPriceFoodLbl);
				break;
			case ORE:
				setLabels(type, player, market, buyOreBtn, sellOreBtn,
						marketOreLbl, buyPriceOreLbl, cargoOreLbl,
						sellPriceOreLbl);
				break;
			case GAMES:
				setLabels(type, player, market, buyGamesBtn, sellGamesBtn,
						marketGamesLbl, buyPriceGamesLbl, cargoGamesLbl,
						sellPriceGamesLbl);
				break;
			case MEDICINE:
				setLabels(type, player, market, buyMedicineBtn,
						sellMedicineBtn, marketMedicineLbl,
						buyPriceMedicineLbl, cargoMedicineLbl,
						sellPriceMedicineLbl);
				break;
			case FIREARMS:
				setLabels(type, player, market, buyFirearmsBtn,
						sellFirearmsBtn, marketFirearmsLbl,
						buyPriceFirearmsLbl, cargoFirearmsLbl,
						sellPriceFirearmsLbl);
				break;
			case MACHINES:
				setLabels(type, player, market, buyMachinesBtn,
						sellMachinesBtn, marketMachinesLbl,
						buyPriceMachinesLbl, cargoMachinesLbl,
						sellPriceMachinesLbl);
				break;
			case NARCOTICS:
				setLabels(type, player, market, buyNarcoticsBtn,
						sellNarcoticsBtn, marketNarcoticsLbl,
						buyPriceNarcoticsLbl, cargoNarcoticsLbl,
						sellPriceNarcoticsLbl);
				break;
			case ROBOTS:
				setLabels(type, player, market, buyRobotsBtn, sellRobotsBtn,
						marketRobotsLbl, buyPriceRobotsLbl, cargoRobotsLbl,
						sellPriceRobotsLbl);
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
	 * Sets the labels corresponding to a given good type
	 * 
	 * @param type
	 *            The good type having its labels set
	 * @param player
	 *            The player that is buying or selling the good
	 * @param market
	 *            The marketplace buying or selling the goods
	 * @param buyButton
	 *            The button that is pressed to initiate a buy
	 * @param sellButton
	 *            The button that is pressed to initiate a sell
	 * @param marketLbl
	 *            The label for the quantity the marketplace has of the good
	 * @param buyPriceLbl
	 *            The label for the price of the good in the marketplace for
	 *            buying
	 * @param cargoLbl
	 *            The label for the quantity of the good the player has in cargo
	 * @param sellPriceLbl
	 *            The label for the price the user can sell a good to the
	 *            marketplace
	 */
	private void setLabels(GoodType type, Player player, Marketplace market,
			Button buyButton, Button sellButton, Label marketLbl,
			Label buyPriceLbl, Label cargoLbl, Label sellPriceLbl) {
		marketLbl.setText(Integer.toString(market.getQuantity(type)));
		int buyPrice = (int) market.getBuyPrice(type, player);
		if (buyPrice != -1)
			buyPriceLbl.setText(Integer.toString(buyPrice));
		else {
			buyPriceLbl.setText("0");
			buyButton.setDisable(true);
		}
		cargoLbl.setText(Integer.toString(player.getShip().amountInCargo(type)));
		int sellPrice = (int) market.getSellPrice(type);
		if (sellPrice != -1)
			sellPriceLbl.setText(Integer.toString(sellPrice));
		else {
			sellPriceLbl.setText("0");
			sellButton.setDisable(true);
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
				Stage stage = (Stage) toPlanetScreenBtn.getScene().getWindow();
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
}