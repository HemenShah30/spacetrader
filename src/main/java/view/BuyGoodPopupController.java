package view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the popup window that allows a user to buy goods
 * 
 * @author Jack Croft
 *
 */
public class BuyGoodPopupController {
	@FXML
	Button buyAllbtn;

	@FXML
	Button buyBtn;

	@FXML
	Button cancelBtn;

	@FXML
	TextField cargoAmountTxt;

	@FXML
	Label cargoAmountLbl;

	/**
	 * Handles the button to buy all of a good
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void buyAllGood(Event e) {
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Buy All Good");
		}
	}

	/**
	 * Handles the button to buy a given amount of a good
	 * 
	 * @param e
	 *            The event that fires the method
	 */
	@FXML
	protected void buyGood(Event e) {
		// need to validate that good entered is an actual number and in the
		// valid range of 1-max
		if (MultiPageController.isValidAction(e)) {
			System.out.println("Buy " + cargoAmountTxt.getText() + " Good");
		}
	}

	/**
	 * Handles the user canceling the buy
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void cancelBuy(Event e) {
		Stage popupStage = (Stage) cancelBtn.getScene().getWindow();
		popupStage.close();
	}
}