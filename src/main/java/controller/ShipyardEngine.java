package controller;

import java.util.ArrayList;
import java.util.List;

import model.Marketplace;
import model.Player;
import model.Ship;
import model.Shipyard;
import model.Enum.GoodType;
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
	public List<String> buy(ShipType type, Shipyard shipyard) {
		double cost = shipyard.getBuyPrice(type);
		List<String> errors = validateBuy(cost);
		if (errors.isEmpty()) {
			//transfer contents of old ship to new ship
			ship = new Ship(type);
			player.setShip(ship);
			player.increaseCredits(getPlayerAssetValue(shipyard));
			player.decreaseCredits(cost);
		}
		return errors;
	}
	
	/**
	 * Internal method for validating a buy transaction
	 * 
	 * @param cost
	 *            The cost of the good
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
	
	private double getPlayerAssetValue(Shipyard shipyard) {
		//player ship value
		double value = shipyard.getSellPrice(ship.getShipType());
		//player cargo value
		
		//player gadget/weapons/shield values
		return value;
	}
}
