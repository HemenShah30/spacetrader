package controller;

import java.util.ArrayList;
import java.util.List;

import model.Player;
import model.Ship;
import model.Shipyard;
import model.Enum.ShipType;

/**
 * Class providing methods for shipyard interactions
 * 
 * @author Larry He
 * 
 */

public class ShipyardEngine {

	private Player player;
	private Ship ship;

	/**
	 * Constructor for the ShipyardEngine, taking in the main game player
	 * 
	 * @param p
	 *            The player that will be trading with the shipyard
	 */
	public ShipyardEngine(Player p) {
		player = p;
		ship = p.getShip();
	}

	/**
	 * Attempts a buy transaction between a shipyard and the player
	 * 
	 * @param type
	 *            The type of ship being bought by the player
	 * @param shipyard
	 *            The shipyard involved in the transaction
	 * @return The errors from the transaction, if any
	 */
	public List<String> buy(ShipType type, Shipyard shipyard,
			double playerAssetValue) {
		double cost = shipyard.getBuyPrice(type) - playerAssetValue;
		List<String> errors = validateBuy(cost);
		if (errors.isEmpty()) {
			double value = playerAssetValue;
			player.setShip(null);
			player.increaseCredits(value);

			ship = new Ship(type);
			player.setShip(ship);
			player.increaseCredits(playerAssetValue);
			player.decreaseCredits(cost);
		}
		return errors;
	}

	/**
	 * Internal method for validating a buy transaction
	 * 
	 * @param cost
	 *            The cost of the ship
	 * @return Any errors from the buy, if any
	 */
	private List<String> validateBuy(double cost) {
		List<String> errors = new ArrayList<String>();

		if (player.getCredits() < cost) {
			errors.add("Not enough credits");
		}
		if (cost == -1)
			errors.add("Planet cannot sell this ship");
		return errors;
	}
}