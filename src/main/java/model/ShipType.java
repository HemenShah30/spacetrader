package model;

/**
 * Enum representing the various ship types
 * 
 * @author Jack Croft
 *
 */
public enum ShipType {
	FLEA(20, 25, 10, 0, 0, 0, 1, 4, 1, 2000, 4, 2, -1, -1, 0, 1, 0), GNAT(14,
			100, 15, 1, 0, 1, 1, 5, 2, 10000, 50, 28, 0, 0, 0, 1, 1), FIREFLY(
			17, 100, 20, 1, 1, 1, 1, 5, 3, 25000, 75, 20, 0, 0, 0, 1, 1), BUMBLEBEE(
			13, 100, 15, 2, 1, 1, 1, 5, 5, 30000, 100, 20, 0, 1, 0, 1, 1), MOSQUITO(
			15, 100, 25, 1, 2, 2, 2, 5, 7, 60000, 125, 15, 0, 1, 0, 1, 2);

	private int fuel;
	private int totalHP;
	private int cargoSize;
	private int weaponSlots;
	private int shieldSlots;
	private int gadgetSlots;
	private int crewSpace;
	private int minTechLevel;
	private int fuelCost;
	private int price;
	private int bounty;
	private int occurrence;
	private int policeModifier;
	private int pirateModifier;
	private int traderModifier;
	private int repairCost;
	private int size;

	/**
	 * Constructor for the ShipType enum, taking in all the parameters related
	 * to the ship
	 * 
	 * @param f
	 *            The fuel capacity for the ship
	 * @param thp
	 *            The Total Hull Points, or health, for the ship
	 * @param cs
	 *            The Cargo Size, or maximum cargo capacity of the ship
	 * @param ws
	 *            The number of weapon slots the ship has
	 * @param ss
	 *            The number of shield slots the ship has
	 * @param gs
	 *            The number of gadget slots the ship has
	 * @param crewS
	 *            The amount of crew space the ship has
	 * @param mtl
	 *            The minimum tech level required for a ship
	 * @param fc
	 *            The cost of fuel for the ship
	 * @param p
	 *            The price of the ship
	 * @param b
	 *            The bounty value for the ship
	 * @param o
	 *            The occurrence of the ship
	 * @param pm
	 *            The modifier for police encounters for the ship
	 * @param pirateM
	 *            The modifier for pirate encounters for the ship
	 * @param tm
	 *            The modifier for trader encounters for the ship
	 * @param rc
	 *            The repair cost for the ship
	 * @param s
	 *            The size of the ship
	 */
	private ShipType(int f, int thp, int cs, int ws, int ss, int gs, int crewS,
			int mtl, int fc, int p, int b, int o, int pm, int pirateM, int tm,
			int rc, int s) {
		fuel = f;
		totalHP = thp;
		cargoSize = cs;
		weaponSlots = ws;
		shieldSlots = ss;
		gadgetSlots = gs;
		crewSpace = cs;
		minTechLevel = mtl;
		fuelCost = fc;
		price = p;
		bounty = b;
		occurrence = o;
		policeModifier = pm;
		pirateModifier = pirateM;
		traderModifier = tm;
		repairCost = rc;
		size = s;
	}

	/**
	 * Returns the fuel capacity for the ship
	 * 
	 * @return The fuel capacity for the ship
	 */
	public int getFuel() {
		return fuel;
	}

	/**
	 * Returns the total hull points for the ship
	 * 
	 * @return The total hull points for the ship
	 */
	public int getTotalHP() {
		return totalHP;
	}

	/**
	 * Returns the cargo size for the ship
	 * 
	 * @return The cargo size for the ship
	 */
	public int getCargoSize() {
		return cargoSize;
	}

	/**
	 * Returns the number of weapon slots for the ship
	 * 
	 * @return The number of weapon slots for the ship
	 */
	public int getWeaponSlots() {
		return weaponSlots;
	}

	/**
	 * Returns the number of shield slots for the ship
	 * 
	 * @return The number of shield slots for the ship
	 */
	public int getShieldSlots() {
		return shieldSlots;
	}

	/**
	 * Returns the number of gadget slots for the ship
	 * 
	 * @return The number of gadget slots for the ship
	 */
	public int getGadgetSlots() {
		return gadgetSlots;
	}

	/**
	 * Returns the amount of crew space for the ship
	 * 
	 * @return The amount of crew space for the ship
	 */
	public int getCrewSpace() {
		return crewSpace;
	}

	/**
	 * Gets the minimum tech level for the ship
	 * 
	 * @return The minimum tech level for the ship
	 */
	public int getMinTechLevel() {
		return minTechLevel;
	}

	/**
	 * Gets the cost of fuel for the ship
	 * 
	 * @return The cost of fuel for the ship
	 */
	public int getFuelCost() {
		return fuelCost;
	}

	/**
	 * Returns the price of the ship
	 * 
	 * @return The price of the ship
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Returns the bounty that gets put on a ship
	 * 
	 * @return The bounty that gets put on a ship
	 */
	public int getBounty() {
		return bounty;
	}

	/**
	 * Returns the occurrence of a ship
	 * 
	 * @return The occurrence of a ship
	 */
	public int getOccurrence() {
		return occurrence;
	}

	/**
	 * Returns the police modifier for encounters for the ship
	 * 
	 * @return The police modifier for encounters for the ship
	 */
	public int getPoliceModifier() {
		return policeModifier;
	}

	/**
	 * Returns the pirate modifier for encounters for the ship
	 * 
	 * @return The pirate modifier for encounters for the ship
	 */
	public int getPirateModifier() {
		return pirateModifier;
	}

	/**
	 * Returns the trader modifier for encounters for the ship
	 * 
	 * @return The trader modifier for encounters for the ship
	 */
	public int getTraderModifier() {
		return traderModifier;
	}

	/**
	 * Gets the repair cost for the ship
	 * 
	 * @return The repair cost for the ship
	 */
	public int getRepairCost() {
		return repairCost;
	}

	/**
	 * Returns the size of the ship
	 * 
	 * @return The size of the ship
	 */
	public int getSize() {
		return size;
	}
}