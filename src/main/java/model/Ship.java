package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Enum.GoodType;
import model.Enum.LaserType;
import model.Enum.ShieldType;
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
	private Map<ShieldType, Integer> shields;
	private List<LaserType> lasers;
	private List<Gadget> gadgets;
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
		shields = new HashMap<ShieldType, Integer>();
		lasers = new ArrayList<LaserType>();
		gadgets = new ArrayList<Gadget>();
	}

	/**
	 * The given amount of damage is dealt to the ship
	 * 
	 * @param damage
	 *            the amount of damage to be done
	 * @throws DeathException
	 *             Thrown when the ship would have 0 or less health after taking
	 *             damage
	 */
	public void takeDamage(int damage) throws DeathException {
		for (ShieldType shield : shields.keySet()) {
			int shieldHP = shields.get(shield);
			int absorbed = Math.min(damage, shieldHP);
			shields.put(shield, shieldHP - absorbed);
			damage -= absorbed;
		}
		currHP -= damage;
		if (currHP <= 0)
			throw new DeathException();
	}

	/**
	 * A given amount of shields is added to the ship
	 * 
	 * @param amount
	 *            The amount the shields need to be increased by
	 */
	public void addShieldHP(int amount) {
		for (ShieldType shield : shields.keySet()) {
			int newShieldHP = Math.min(amount + shields.get(shield),
					shield.getShieldHP());
			amount -= newShieldHP - shields.get(shield);
			shields.put(shield, newShieldHP);
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
	 * Returns the current shield hp of the ship
	 * 
	 * @return The current shield hp of the ship
	 */
	public int getCurrShieldHP() {
		int sum = 0;
		for (int shieldHP : shields.values()) {
			sum += shieldHP;
		}
		return sum;
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
	 * Calculates and returns amount needed to refuel
	 * @return cost to refuel ship
	 */
	public double getCostRefuel() {
		return (shipType.getFuel() - fuel) * shipType.getFuelCost();
	}
	/**
	 * Calculates and returns amount needed to repair
	 * @return
	 */
	public double getCostRepair() {
		return (shipType.getTotalHP() - currHP) *shipType.getRepairCost();
	}
	
	/**
	 * Refuels ship
	 * @param credits used in refueling
	 */
	public void refuel(double credits) {
		fuel += (credits / shipType.getFuelCost());
	}
	/**
	 * Repairs ships
	 * @param credits amount used in the repair
	 */
	public void repair(double credits) {
		currHP += (credits / shipType.getRepairCost());
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
	 * Adds a shield to the ship
	 * 
	 * @param shield
	 *            The shield to be added to the ship
	 * @throws MaxCapacityException
	 *             Thrown when too many shields are added
	 */
	public void addShield(ShieldType shield) throws MaxCapacityException {
		if (shipType.getShieldSlots() > shields.size()) {
			shields.put(shield, shield.getShieldHP());
			return;
		}
		throw new MaxCapacityException("Already has max shields");
	}

	/**
	 * Adds a laser to the ship
	 * 
	 * @param laser
	 *            The laser to be added to the ship
	 * @throws MaxCapacityException
	 *             Thrown when too many lasers are added
	 */
	public void addLaser(LaserType laser) throws MaxCapacityException {
		if (shipType.getWeaponSlots() > lasers.size()) {
			lasers.add(laser);
			return;
		}
		throw new MaxCapacityException("Already has max lasers");
	}

	/**
	 * Adds a gadget to the ship
	 * 
	 * @param g
	 *            The gadget to be added to the ship
	 * @throws MaxCapacityException
	 *             thrown when too many gadgets are added
	 */
	public void addGadget(Gadget g) throws MaxCapacityException {
		if (shipType.getGadgetSlots() > gadgets.size()) {
			gadgets.add(g);
			return;
		}
		throw new MaxCapacityException("Already has max gadgets");
	}

	/**
	 * Returns all lasers currently equipped by ship
	 * 
	 * @return The lasers equipped by the ship
	 */
	public List<LaserType> getLasers() {
		return lasers;
	}

	/**
	 * Returns the shields equipped by the ship
	 * 
	 * @return The shields equipped by the ship
	 */
	public List<ShieldType> getShields() {
		return new ArrayList<ShieldType>(shields.keySet());
	}

	/**
	 * Returns the gadgets equipped by the ship
	 * 
	 * @return The gadgets equipped by the ship
	 */
	public List<Gadget> getGadgets() {
		return gadgets;
	}
}