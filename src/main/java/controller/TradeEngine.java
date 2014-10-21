package controller;

import java.util.ArrayList;
import java.util.List;

import model.Marketplace;
import model.Player;
import model.Ship;
import model.Trader;
import model.Enum.GoodType;

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
	 *            The player that will be trading with the marketplace
	 */
	public TradeEngine(Player p) {
		player = p;
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
		double cost = market.getBuyPrice(tradeGood, player) * quantity;
		List<String> errors = validateBuy(cost, quantity);
		if (errors.isEmpty()) {
			ship.addToCargo(tradeGood, quantity);
			player.decreaseCredits(cost);
			market.setQuantity(tradeGood, market.getQuantity(tradeGood)
					- quantity);
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
		if (cost == -1)
			errors.add("Planet cannot sell this good");
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
		double cost = market.getSellPrice(tradeGood) * quantity;
		List<String> errors = validateSell(tradeGood, quantity, market);
		if (errors.isEmpty()) {
			if (market.getBuyPrice(tradeGood, player) != -1) {
				market.setQuantity(tradeGood, market.getQuantity(tradeGood)
						+ quantity);
			}
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
	private List<String> validateSell(GoodType tradeGood, int quantity,
			Marketplace market) {
		List<String> errors = new ArrayList<String>();

		if (ship.amountInCargo(tradeGood) == 0) {
			errors.add("You have no " + tradeGood + " to sell");
		} else if (ship.amountInCargo(tradeGood) - quantity < 0) {
			errors.add("You do not have that many of " + tradeGood);
		}
		if (market.getSellPrice(tradeGood) == -1)
			errors.add("Planet cannot purchase this good");

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
		if (market.getBuyPrice(good, player) == -1)
			return 0;
		return Math.min(Math.min(ship.getCargoSize() - ship.getCurrCargo(),
				market.getQuantity(good)),
				((int) player.getCredits() / (int) market.getBuyPrice(good,
						player)));
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
		Marketplace market = player.getPlanet().getMarketplace();
		if (market.getSellPrice(good) == -1)
			return 0;
		return ship.amountInCargo(good);
	}

	/**
	 * Returns the maximum amount of good the given trader can buy or sell with
	 * the player
	 * 
	 * @param trader
	 *            The trader trading with the player
	 * @return The maximum transaction amount with the player
	 */
	public int getMaximumTraderTradeAmount(Trader trader) {
		return 10;
	}

	/**
	 * Executes a trade with a trader if possible, or returns a list of error
	 * strings
	 * 
	 * @param trader
	 *            The trader who the player is trading with
	 * @param quantity
	 *            The quantity of the good the player is trying to trade
	 * @return The list of errors, if any, with the trade
	 */
	public List<String> tradeWithTrader(Trader trader, int quantity) {
		return new ArrayList<String>();
	}
}