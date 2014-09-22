/**
 * Class representing a ship
 * 
 * @author Eric Wan
 * 
 */
package model;

import java.util.Map;

/**
 * @author Eric Wan
 *
 */
public class Ship {
	// private ShipType shipType;
	private int totalHP, currHP, currFuel, cargoSize;
	// private List<Mercenary> mercenaries;
	private Map<TradeGood, Integer> cargo;

	/**
	 * Constructor class for Ship taking all appropriate instance variables
	 * 
	 * @param totalHP
	 *            The total HP of the ship
	 * @param currHP
	 *            The current HP of the ship
	 * @param currFuel
	 *            The current fuel of the ship
	 * @param cargo
	 *            The current cargo of the ship
	 */
	public Ship(int totalHP, int currHP, int currFuel, int cargoSize,
			Map<TradeGood, Integer> cargo) {
		this.totalHP = totalHP;
		this.currHP = currHP;
		this.currFuel = currFuel;
		this.cargoSize = cargoSize;
		this.cargo = cargo;
		// this.shipType = shipType;
		// this.mercenaries = mercenaries;
	}

	/**
	 * Returns totalHP of ship
	 * 
	 * @return the totalHP of the ship
	 */
	public int getTotalHP() {
		return totalHP;
	}

	/**
	 * Returns currentHP of ship
	 * 
	 * @return the currentHP of the ship
	 */
	public int getCurrHP() {
		return currHP;
	}

	/**
	 * Returns the cargo contents of ship
	 * 
	 * @return the cargo contents of the ship
	 */
	public Map<TradeGood, Integer> getCargo() {
		return cargo;
	}

	/**
	 * Returns the current fuel of ship
	 * 
	 * @return the current fuel of the ship
	 */
	public int getCurrFuel() {
		return currFuel;
	}

	/**
	 * Returns the size of the cargo hold of the ship
	 * 
	 * @return the size of the cargo hold of the ship
	 */
	public int getCargoSize() {
		return cargoSize;
	}

	// /**
	// * Returns the ship type of ship
	// *
	// * @return the shipType of the ship
	// */
	// public ShipType getShipType() {
	// return shipType;
	// }

	// /**
	// * Returns the list of mercenaries
	// *
	// * @return the list of mercenaries
	// */
	// public List<Mercenary> getMercenaries() {
	// return mercenaries;
	// }

	/**
	 * Adds a good to the cargo of the ship
	 * 
	 * @param TradeGood
	 *            The good type
	 * @param Integer
	 *            The number of this good being added
	 */
	public void addToCargo(TradeGood goodType, Integer quantity) {
		// check capacity
		if (cargo.containsKey(goodType)) {
			Integer temp = cargo.get(goodType);
			if (temp + quantity > cargoSize) {
				// present error
			} else {
				cargo.put(goodType, temp + quantity);
			}
		} else {
			if (quantity > cargoSize) {
				// present error
			} else {
				cargo.put(goodType, quantity);
			}
		}
	}

	/**
	 * Removes a good from the cargo of the ship
	 * 
	 * @param TradeGood
	 *            The good type
	 * @param Integer
	 *            The number of this good being removed
	 */
	public void removeFromCargo(TradeGood goodType, Integer quantity) {
		if (cargo.containsKey(goodType)) {
			Integer temp = cargo.get(goodType);
			if (quantity > temp) {
				// present error
			} else {
				cargo.put(goodType, temp - quantity);
			}
		} else {
			// present error
		}
	}
}
