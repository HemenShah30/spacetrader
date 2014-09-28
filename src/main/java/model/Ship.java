package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a player's ship
 * 
 * @author Eric Wan
 * 
 */
public class Ship {
	private ShipType shipType;
	private int totalHP, currHP, fuel, cargoSize, currCargo;
	// private List<Mercenary> mercenaries;
	private Map<GoodType, Integer> cargo;

	/**
	 * Constructor for a ship taking in the starting fuel and the ship type
	 * 
	 * @param f
	 *            The starting fuel for the ship
	 * @param sT
	 *            The ShipType for the ship
	 */
	public Ship(ShipType sT) {
		setShipType(sT);
		cargo = new HashMap<GoodType, Integer>();
		for (GoodType type : GoodType.values())
			cargo.put(type, 0);
		fuel = sT.getFuel();
		totalHP = sT.getTotalHP();
		currHP = totalHP;
		cargoSize = sT.getCargoSize();
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
	public Map<GoodType, Integer> getCargo() {
		return cargo;
	}

	/**
	 * Returns the current fuel of ship
	 * 
	 * @return the current fuel of the ship
	 */
	public int getFuel() {
		return fuel;
	}

	/**
	 * Sets currFuel of the ship
	 */
	public void setFuel(int f) {
		this.fuel = f;
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
	public void addToCargo(GoodType goodType, int quantity) {
		int currentGoodCargo = cargo.get(goodType);
		cargo.put(goodType, currentGoodCargo + quantity);
		currCargo += quantity;
	}

	/**
	 * Removes a good from the cargo of the ship
	 * 
	 * @param goodType
	 *            The good type
	 * @param Integer
	 *            The number of this good being removed
	 */
	public void removeFromCargo(GoodType goodType, int quantity) {
		int currentGoodCargo = cargo.get(goodType);
		cargo.put(goodType, currentGoodCargo - quantity);
		currCargo -= quantity;
	}

	/**
	 * Returns quantity of a certain good in cargo (will be 0 if doesn't exist)
	 * 
	 * @param goodType
	 *            The GoodType being checked
	 * @return The amount of the good type in cargo
	 */
	public int amountInCargo(GoodType goodType) {
		if (!cargo.containsKey(goodType)) {
			return 0;
		}
		return cargo.get(goodType);
	}

	/**
	 * Getter for the ship type for the ship
	 * 
	 * @return The ship type for the ship
	 */
	public ShipType getShipType() {
		return shipType;
	}

	/**
	 * Private setter for the ship type of the ship
	 * 
	 * @param sT
	 */
	private void setShipType(ShipType sT) {
		if (sT == null)
			throw new IllegalArgumentException();
		shipType = sT;
	}
}