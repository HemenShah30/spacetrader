package view;

import java.io.IOException;

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
	private Label buyPriceGame;
	@FXML
	private Label sellPriceFirearm;
	@FXML
	private Button buyWaterBtn;
	@FXML
	private Button sellMedicineBtn;
	@FXML
	private Label buyPriceNarcotics;
	@FXML
	private Label buyPriceFur;
	@FXML
	private Label marketNarcotics;
	@FXML
	private Button sellFursBtn;
	@FXML
	private Label sellPriceMachine;
	@FXML
	private Label sellPriceNarcotics;
	@FXML
	private Button sellNarcoticsBtn;
	@FXML
	private Label buyPriceRobot;
	@FXML
	private Label buyPriceFood;
	@FXML
	private Label buyPriceMachine;
	@FXML
	private Label sellPriceMedicine;
	@FXML
	private Button sellGamesBtn;
	@FXML
	private Button buyRobotsBtn;
	@FXML
	private Label cargoFur;
	@FXML
	private Label cargoMedicine;
	@FXML
	private Label marketFirearms;
	@FXML
	private Button sellFirearmsBtn;
	@FXML
	private Label sellPriceWater;
	@FXML
	private Label cargoWater;
	@FXML
	private Label buyPriceFirearm;
	@FXML
	private Label marketFood;
	@FXML
	private Label marketWater;
	@FXML
	private Label sellPriceRobot;
	@FXML
	private Label cargoMachines;
	@FXML
	private Label sellPriceFood;
	@FXML
	private Button sellRobotsBtn;
	@FXML
	private Label cargoOre;
	@FXML
	private Button sellMachinesBtn;
	@FXML
	private Label cargoNarcotics;
	@FXML
	private Button buyFoodBtn;
	@FXML
	private Label cargoRobots;
	@FXML
	private Label marketGames;
	@FXML
	private Button sellFoodBtn;
	@FXML
	private Label marketOre;
	@FXML
	private Button buyMedicineBtn;
	@FXML
	private Label buyPriceOre;
	@FXML
	private Label cargoGames;
	@FXML
	private Button buyFirearmsBtn;
	@FXML
	private Label marketRobots;
	@FXML
	private Label sellPriceOre;
	@FXML
	private Label buyPriceMedicine;
	@FXML
	private Label buyPriceWater;
	@FXML
	private Label cargoFood;
	@FXML
	private Button buyGamesBtn;
	@FXML
	private Label sellPriceGames;
	@FXML
	private Button sellWaterBtn;
	@FXML
	private Button sellOreBtn;
	@FXML
	private Button buyFursBtn;
	@FXML
	private Label marketFur;
	@FXML
	private Button buyOreBtn;
	@FXML
	private Button buyNarcoticsBtn;
	@FXML
	private Label cargoFirearms;
	@FXML
	private Label sellPriceFur;
	@FXML
	private Label marketMedicine;
	@FXML
	private Label marketMachines;
	@FXML
	private Button buyMachinesBtn;

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
			try {
				Stage buyPopup = new Stage();
				buyPopup.initModality(Modality.APPLICATION_MODAL);
				buyPopup.initOwner((Stage) btn.getScene().getWindow());

				Parent newScene = new FXMLLoader().load(ClassLoader
						.getSystemResourceAsStream("view/BuyGoodPopup.fxml"));
				buyPopup.setScene(new Scene(newScene, 300, 125));
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
			System.out.println("Selling good");
		}
	}
}