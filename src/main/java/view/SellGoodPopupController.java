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
 * Controller for the popup window that allows a user to sell goods
 * 
 * @author Jack Croft
 *
 */
public class SellGoodPopupController {
	private int sellAllAmount;
	private GoodType tradeGood;
	private GameEngine game;
	private TradeScreenController tradeScreen;

	@FXML
	Button sellAllbtn;

	@FXML
	Button sellBtn;

	@FXML
	Button cancelBtn;

	@FXML
	TextField cargoAmountTxt;

	@FXML
	Label cargoAmountLbl;

	/**
	 * Handles the button to sell all of a good
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void sellAllGood(Event e) {
		if (MultiPageController.isValidAction(e)) {
			List<String> errors = game.marketplaceTrade(tradeGood,
					sellAllAmount, false);
			if (errors.isEmpty()) {
				tradeScreen.updatePage();
				Stage popupStage = (Stage) sellBtn.getScene().getWindow();
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
	 * Handles the button to sell a given amount of a good
	 * 
	 * @param e
	 *            The event that fires the method
	 */
	@FXML
	protected void sellGood(Event e) {
		if (MultiPageController.isValidAction(e)) {
			int goodAmount;
			try {
				goodAmount = Integer.parseInt(cargoAmountTxt.getText());
				if (goodAmount > 0 && goodAmount <= sellAllAmount) {
					List<String> errors = game.marketplaceTrade(tradeGood,
							goodAmount, false);
					if (errors.isEmpty()) {
						tradeScreen.updatePage();
						Stage popupStage = (Stage) sellBtn.getScene()
								.getWindow();
						popupStage.close();
					} else {
						String errorMsg = "";
						for (String error : errors)
							errorMsg += error + "\n";
						displayError(errorMsg);
					}

				} else {
					if (sellAllAmount == 0)
						displayError("You have no " + tradeGood + " to sell");
					else if (sellAllAmount == 1)
						displayError("You only have 1 " + tradeGood
								+ " to sell");
					else
						displayError("You must enter a number between 1 and "
								+ sellAllAmount);
				}

			} catch (NumberFormatException nfe) {
				displayError("You must enter a number for the sell amount");
			}
		}
	}

	/**
	 * Handles the user canceling the sell
	 * 
	 * @param e
	 *            The event that fired the method
	 */
	@FXML
	protected void cancelBuy(Event e) {
		Stage popupStage = (Stage) cancelBtn.getScene().getWindow();
		popupStage.close();
	}

	/**
	 * Method to initialize the page with the proper initial values
	 * 
	 * @param sellAll
	 *            The max selling amount for the cargo
	 * @param good
	 *            The GoodType to be sold
	 */
	public void initializePage(GoodType good, TradeScreenController ts) {
		game = GameEngine.getGameEngine();
		tradeScreen = ts;
		sellAllAmount = game.getMaximumGood(good, false);
		tradeGood = good;
		cargoAmountLbl.setText("Enter amount or Sell All to sell "
				+ sellAllAmount + " " + good);
	}

	private void displayError(String msg) {
		Dialogs.create().owner(sellBtn.getScene().getWindow()).title("Error")
				.message(msg).showError();
	}
}