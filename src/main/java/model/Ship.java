package model;

import java.util.HashMap;
import java.util.Map;

import model.Enum.GoodType;
import model.Enum.LaserType;
import model.Enum.ShipType;

/**
 * Class representing a player's ship
 * 
 * @author Eric Wan
 * 
 */
public class Ship {
	private ShipType shipType;
	private int currHP, fuel, cargoSize;
	// private List<Mercenary> mercenaries;
	private Map<GoodType, Integer> cargo;

	/**
	 * Constructor for a ship taking in the starting fuel and the ship type
	 * 
	 * @param sT
	 *            The ShipType for the ship
	 */
	public Ship(ShipType sT) {
		this(sT, sT.getTotalHP(), sT.getFuel());
	}

	/**
	 * Ship constructor taking in values for more instance variables
	 * 
	 * @param sT
	 *            The ShipType for the ship
	 * @param hp
	 *            The Hull Points for the ship
	 * @param f
	 *            The fuel for the ship
	 */
	public Ship(ShipType sT, int hp, int f) {
		setShipType(sT);
		cargo = new HashMap<GoodType, Integer>();
		for (GoodType type : GoodType.values())
			cargo.put(type, 0);
		fuel = f;
		currHP = hp;
		cargoSize = sT.getCargoSize();
	}

	/**
	 * A simpler way of calculating damage to be taken
	 * 
	 * @param damage
	 *            the amount of damage to be done
	 */
	public void takeDamage(int damage) {
		int remaining = damage;
		while (remaining > 0) {
			// int shield = getShield().getCurrHP();
			int hull = getCurrHP();
			// if (shield > 0) {
			// getShield().setCurrHP(shield - 1);
			// } else {
			// setCurrHP(hull - 1)
			// }
			remaining--;
		}
	}

	/**
	 * Returns totalHP of ship
	 * 
	 * @return the totalHP of the ship
	 */
	public int getTotalHP() {
		return shipType.getTotalHP();
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
	 * Sets the current hull points for the ship
	 * 
	 * @param currHP
	 *            The new hull points for the ship
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
	 * Sets the fuel the ship currently has
	 * 
	 * @param f
	 *            The new fuel for the ship
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
	 * Sets the cargo size for the ship
	 * 
	 * @param cargoSize
	 *            The new cargo size for the ship
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
		int currCargo = 0;
		for (GoodType good : cargo.keySet())
			currCargo += cargo.get(good);

		return currCargo;
	}

	/**
	 * Adds a good to the cargo of the ship
	 * 
	 * @param goodType
	 *            The good type that is being added to the ship's cargo
	 * @param quantity
	 *            The number of this good being added
	 */
	public void addToCargo(GoodType goodType, int quantity) {
		int currentGoodCargo = cargo.get(goodType);
		cargo.put(goodType, currentGoodCargo + quantity);
	}

	/**
	 * Removes a good from the cargo of the ship
	 * 
	 * @param goodType
	 *            The good type being removed from this ship's cargo
	 * @param quantity
	 *            The number of this good being removed
	 */
	public void removeFromCargo(GoodType goodType, int quantity) {
		int currentGoodCargo = cargo.get(goodType);
		cargo.put(goodType, currentGoodCargo - quantity);
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
	 *            The ship type the ship will have
	 */
	private void setShipType(ShipType sT) {
		if (sT == null)
			throw new IllegalArgumentException();
		shipType = sT;
	}

	/**
	 * Returns all lasers currently equipped by ship
	 * 
	 * @return array of lasers
	 */
	public LaserType[] getLasers() {
		// TODO Auto-generated method stub
		return null;
	}
}