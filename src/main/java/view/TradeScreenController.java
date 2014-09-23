package view;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	Button buyWaterBtn;

	@FXML
	Button buyFursBtn;

	@FXML
	Button buyFoodBtn;

	@FXML
	Button buyOreBtn;

	@FXML
	Button buyGamesBtn;

	@FXML
	Button buyMedicineBtn;

	@FXML
	Button buyFirearmsBtn;

	@FXML
	Button buyMachinesBtn;

	@FXML
	Button buyNarcoticsBtn;

	@FXML
	Button buyRobotsBtn;

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
}