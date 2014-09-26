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
 *         Class representing a player's ship
 */
public class Ship {
	// private ShipType shipType;
	private int totalHP, currHP, currFuel, cargoSize, currCargo;
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
		setTotalHP(totalHP);
		setCurrHP(currHP);
		setCurrFuel(currFuel);
		setCargoSize(cargoSize);
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
	 * Sets totalHP of ship
	 */
	public void setTotalHP(int totalHP) {
		this.totalHP = totalHP;
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
	 * Sets currentHP of ship
	 */
	public void setCurrHP(int currHP) {
		this.currHP = currHP;
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
	 * Sets currFuel of the ship
	 */
	public void setCurrFuel(int currFuel) {
		this.currFuel = currFuel;
	}

	/**
	 * Returns the size of the cargo hold of the ship
	 * 
	 * @return the size of the cargo hold of the ship
	 */
	public int getCargoSize() {
		return cargoSize;
	}

	/**
	 * Sets cargoSize of the ship
	 */
	public void setCargoSize(int cargoSize) {
		this.cargoSize = cargoSize;
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
	 * Returns the current cargo capacity of the ship
	 * 
	 * @return the current cargo capacity of the ship
	 */
	public int getCurrCargo() {
		return currCargo;
	}

	/**
	 * Adds a good to the cargo of the ship
	 * 
	 * @param TradeGood
	 *            The good type
	 * @param Integer
	 *            The number of this good being added
	 */
	public void addToCargo(TradeGood goodType, Integer quantity) {
		cargo.put(goodType, quantity);
		currCargo += quantity;
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
		Integer temp = cargo.get(goodType);
		cargo.put(goodType, temp - quantity);
		currCargo -= quantity;
	}

	/**
	 * Returns quantity of a certain good in cargo (will be 0 if doesn't exist)
	 * 
	 * @param TradeGood
	 *            The good type
	 */
	public int amountInCargo(TradeGood goodType) {
		if (!cargo.containsKey(goodType)) {
			return 0;
		}
		return cargo.get(goodType);
	}

}
