package view;

import java.util.List;

import org.controlsfx.dialog.Dialogs;

import controller.GameEngine;
import model.GoodType;
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
	private int buyAllAmount;
	private GoodType tradeGood;
	private GameEngine game;
	private TradeScreenController tradeScreen;

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
			List<String> errors = game.marketplaceTrade(tradeGood,
					buyAllAmount, true);
			if (errors.isEmpty()) {
				tradeScreen.updatePage();
				Stage popupStage = (Stage) buyBtn.getScene().getWindow();
				popupStage.close();
			} else {
				String errorMsg = "";
				for (String error : errors)
					errorMsg += error + "\n";
				displayError(errorMsg);
			}
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
		if (MultiPageController.isValidAction(e)) {
			int goodAmount;
			try {
				goodAmount = Integer.parseInt(cargoAmountTxt.getText());
				if (goodAmount > 0 && goodAmount <= buyAllAmount) {
					List<String> errors = game.marketplaceTrade(tradeGood,
							goodAmount, true);
					if (errors.isEmpty()) {
						tradeScreen.updatePage();
						Stage popupStage = (Stage) buyBtn.getScene()
								.getWindow();
						popupStage.close();
					} else {
						String errorMsg = "";
						for (String error : errors)
							errorMsg += error + "\n";
						displayError(errorMsg);
					}

				} else {
					displayError("You must enter a number between 1 and "
							+ buyAllAmount);
				}

			} catch (NumberFormatException nfe) {
				displayError("You must enter a number for the buy amount");
			}
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

	public void initializePage(GoodType good, TradeScreenController ts) {
		game = GameEngine.getGameEngine();
		tradeScreen = ts;
		buyAllAmount = game.getMaximumGood(good, true);
		tradeGood = good;
		cargoAmountLbl.setText("Enter amount or Buy All for " + buyAllAmount
				+ " " + good);
	}

	private void displayError(String msg) {
		Dialogs.create().owner(buyBtn.getScene().getWindow()).title("Error")
				.message(msg).showError();
	}
}