package view;

import java.util.List;

import org.controlsfx.dialog.Dialogs;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import controller.GameEngine;
import model.enums.GoodType;

/**
 * Controller for the popup window that allows a user to buy or sell goods, with the marketplace or
 * with a trader
 * 
 * @author Jack Croft
 *
 */
public class TradeGoodPopupController {
    private int maxTradeAmount;
    private GoodType tradeGood;
    private GameEngine game;
    private boolean isBuying;
    private boolean isMarketplace;
    private Controller parentController;

    @FXML
    private Button tradeAllBtn;

    @FXML
    private Button tradeBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField cargoAmountTxt;

    @FXML
    private Label cargoLbl;

    /**
     * Handles the buying and selling of all available goods
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void tradeAllGoods(Event event) {
        if (MultiPageController.isValidAction(event)) {
            List<String> errors = trade(maxTradeAmount);
            if (errors.isEmpty()) {
                parentController.updatePage();
                Stage popupStage = (Stage) tradeBtn.getScene().getWindow();
                popupStage.close();
            } else {
                String errorMsg = "";
                for (String error : errors) {
                    errorMsg += error + "\n";
                }
                displayError(errorMsg);
            }
        }
    }

    /**
     * Handles the buying or selling of some amount of good
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void tradeGood(Event event) {
        if (MultiPageController.isValidAction(event)) {
            int goodAmount;
            try {
                goodAmount = Integer.parseInt(cargoAmountTxt.getText());
                if (goodAmount > 0 && goodAmount <= maxTradeAmount) {
                    List<String> errors = trade(goodAmount);
                    if (errors.isEmpty()) {
                        parentController.updatePage();
                        Stage popupStage = (Stage) tradeBtn.getScene().getWindow();
                        popupStage.close();
                    } else {
                        String errorMsg = "";
                        for (String error : errors) {
                            errorMsg += error + "\n";
                        }
                        displayError(errorMsg);
                    }

                } else {
                    if (maxTradeAmount == 0) {
                        displayError("You cannot " + (isBuying ? "buy" : "sell") + " any "
                                + tradeGood);
                    } else if (maxTradeAmount == 1) {
                        displayError("You can only  " + (isBuying ? "buy" : "sell") + " 1 "
                                + tradeGood);
                    } else {
                        displayError("You must enter a number between 1 and " + maxTradeAmount);
                    }
                }

            } catch (NumberFormatException nfe) {
                displayError("You must enter a number for the " + (isBuying ? "buy" : "sell")
                        + " amount");
            }
        }
    }

    /**
     * Handles the canceling of a trade
     * 
     * @param e
     *            The event that fired the method
     */
    @FXML
    private void cancelTrade(Event event) {
        Stage popupStage = (Stage) cancelBtn.getScene().getWindow();
        popupStage.close();
    }

    /**
     * Method that represents a trade attempt returning any errors in the trade
     * 
     * @return Any errors that occurred in the trade
     */
    private List<String> trade(int amount) {
        if (isMarketplace) {
            return game.tradeWithMarketplace(tradeGood, amount, isBuying);
        } else {
            return game.tradeWithTrader(((NPCEncounterController) parentController).getEncounter(),
                    amount);
        }
    }

    /**
     * Initializes the page with the given values
     * 
     * @param good
     *            The GoodType being bought or sold
     * @param c
     *            The parent Controller of this page
     * @param maximumTradeAmount
     *            The maximum amount of a good that can be bought or sold
     * @param buying
     *            Whether or not the player is buying the given good
     * @param marketplace
     *            Whether or not the player is at a marketplace trading
     */
    public void initializePage(GoodType good, Controller con, int maximumTradeAmount,
            boolean buying, boolean marketplace) {
        game = GameEngine.getGameEngine();
        parentController = con;
        isBuying = buying;
        tradeGood = good;
        isMarketplace = marketplace;
        maxTradeAmount = maximumTradeAmount;
        tradeBtn.setText(isBuying ? "Buy" : "Sell");
        tradeAllBtn.setText(isBuying ? "Buy All" : "Sell All");
        cargoLbl.setText("Enter amount or " + (isBuying ? "Buy" : "Sell") + " All for "
                + maxTradeAmount + " " + good);
    }

    /**
     * Creates a dialog error box with the given message
     * 
     * @param msg
     *            The message for the error dialog to display
     */
    private void displayError(String msg) {
        Dialogs.create().owner(tradeBtn.getScene().getWindow()).title("Error").message(msg)
                .showError();
    }
}