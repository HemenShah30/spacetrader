package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class providing methods for trade interactions
 * 
 * @author Eric Wan
 * 
 */
public class TradeEngine {

	private Player p;
	private Ship ship;

	/**
	 * Constructor for the TradeEngine, taking in the main game player
	 * 
	 * @param p
	 */
	public TradeEngine(Player p) {
		this.p = p;
		ship = p.getShip();
	}

	/**
	 * Attempts a buy transaction between a marketplace and the player
	 * 
	 * @param tradeGood
	 *            The good being bought by the player
	 * @param quantity
	 *            The quantity of the good being bought
	 * @param market
	 *            The marketplace involved in the transaction
	 * @return The errors from the transaction, if any
	 */
	public List<String> buy(TradeGood tradeGood, int quantity,
			Marketplace market) {
		double cost = market.generatePrice(tradeGood) * quantity;
		List<String> errors = validateBuy(cost, quantity);
		if (errors.isEmpty()) {
			ship.addToCargo(tradeGood, quantity);
			p.decreaseCredits(cost);
		}
		return errors;
	}

	/**
	 * Internal method for validating a buy transaction
	 * 
	 * @param cost
	 *            The cost of the good
	 * @param quantity
	 *            The quantity of the good
	 * @return Any errors from the buy, if any
	 */
	private List<String> validateBuy(double cost, int quantity) {
		List<String> errors = new ArrayList<String>();

		if (p.getCredits() < cost) {
			errors.add("Not enough credits");
		}
		if (ship.getCurrCargo() + quantity > ship.getCargoSize()) {
			errors.add("Not enough space");
		}

		return errors;
	}

	/**
	 * Attempts a sell transaction between the player and the marketplace
	 * 
	 * @param tradeGood
	 *            The good being sold by the player
	 * @param quantity
	 *            The quantity of the good being sold
	 * @param market
	 *            the marketplace involved in the transaction
	 * @return The errors from the sell, if any
	 */
	public List<String> sell(TradeGood tradeGood, int quantity,
			Marketplace market) {
		double cost = market.generatePrice(tradeGood) * quantity;
		List<String> errors = validateSell(tradeGood, quantity);
		if (errors.isEmpty()) {
			ship.removeFromCargo(tradeGood, quantity);
			p.increaseCredits(cost);
		}
		return errors;
	}

	/**
	 * Validates a sell transaction
	 * 
	 * @param tradeGood
	 *            The good being sold by the user
	 * @param quantity
	 *            The quantity of the good being sold
	 * @return The errors from the sell, if any
	 */
	private List<String> validateSell(TradeGood tradeGood, int quantity) {
		List<String> errors = new ArrayList<String>();

		if (ship.amountInCargo(tradeGood) == 0) {
			errors.add("You have no " + tradeGood.toString());
		} else if (ship.amountInCargo(tradeGood) - quantity < 0) {
			errors.add("You do not have that many of " + tradeGood.toString());
		}

		return errors;
	}
}