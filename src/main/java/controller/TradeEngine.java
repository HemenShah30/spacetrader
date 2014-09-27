package controller;

import java.util.ArrayList;
import java.util.List;

import model.GoodType;
import model.Marketplace;
import model.Player;
import model.Ship;

/**
 * Class providing methods for trade interactions
 * 
 * @author Eric Wan
 * 
 */
public class TradeEngine {

	private Player player;
	private Ship ship;

	/**
	 * Constructor for the TradeEngine, taking in the main game player
	 * 
	 * @param p
	 */
	public TradeEngine(Player p) {
		this.player = p;
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
	public List<String> buy(GoodType tradeGood, int quantity, Marketplace market) {
		double cost = market.getPrice(tradeGood) * quantity;
		List<String> errors = validateBuy(cost, quantity);
		if (errors.isEmpty()) {
			ship.addToCargo(tradeGood, quantity);
			player.decreaseCredits(cost);
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

		if (player.getCredits() < cost) {
			errors.add("Not enough credits");
		}
		if (ship.getCurrCargo() + quantity > ship.getCargoSize()) {
			errors.add("Not enough space in your ship");
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
	public List<String> sell(GoodType tradeGood, int quantity,
			Marketplace market) {
		double cost = market.getPrice(tradeGood) * quantity;
		List<String> errors = validateSell(tradeGood, quantity);
		if (errors.isEmpty()) {
			ship.removeFromCargo(tradeGood, quantity);
			player.increaseCredits(cost);
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
	private List<String> validateSell(GoodType tradeGood, int quantity) {
		List<String> errors = new ArrayList<String>();

		if (ship.amountInCargo(tradeGood) == 0) {
			errors.add("You have no " + tradeGood + " to sell");
		} else if (ship.amountInCargo(tradeGood) - quantity < 0) {
			errors.add("You do not have that many of " + tradeGood);
		}

		return errors;
	}

	/**
	 * Returns the maximum amount of a good a player can buy from the
	 * marketplace
	 * 
	 * @param good
	 *            The good being bought
	 * @return The amount of the good the user can buy
	 */
	public int getMaximumBuyGoodAmount(GoodType good) {
		Marketplace market = player.getPlanet().getMarketplace();
		return Math.min(
				Math.min(ship.getCargoSize() - ship.getCurrCargo(),
						market.getQuantity(good)),
				((int) player.getCredits() / (int) market.getPrice(good)));
	}

	/**
	 * Returns the maximum amount of a good that a player can sell to a
	 * marketplace
	 * 
	 * @param good
	 *            The good being sold
	 * @return The amount of a good that a user can sell
	 */
	public int getMaximumSellGoodAmount(GoodType good) {
		return ship.amountInCargo(good);
	}
}