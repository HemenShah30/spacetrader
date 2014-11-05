package model.Enum;

import model.Sellable;
import model.Ship;

/**
 * Enum representing the various lasers that a ship can have
 * 
 * @author Caroline Zhang
 *
 */
public enum LaserType implements Sellable {

	PULSELASER(20, 5000, 4, 0, 0, 10, 40, 60, 101), BEAMLASER(35, 15000, 5, 20,
			30, 30, 101, 101, 101), MILITARYLASER(60, 35000, 6, 40, 40, 50,
			101, 101, 101);

	private int baseDamage;
	private int minTechLevel;
	private double price;
	private int minPoliceRep;
	private int minPirateRep;
	private int minTraderRep;
	private int maxPoliceRep;
	private int maxPirateRep;
	private int maxTraderRep;

	/**
	 * Constructor for the Laser Enum
	 * 
	 * @param d
	 *            The damage dealt by the laser
	 * @param p
	 *            The price of the laser
	 * @param mtl
	 *            The minimum tech level required for a laser
	 * @param minPoliceRep
	 *            The minimum Police rep needed for the police to have this
	 *            laser
	 * @param minPirateRep
	 *            The minimum Pirate rep needed for the pirate to have this
	 *            laser
	 * @param minTraderRep
	 *            The minimum Trader rep needed for the trader to have this
	 *            laser
	 * @param maxPoliceRep
	 *            The maximum Police rep needed for the police to have this
	 *            laser
	 * @param maxPirateRep
	 *            The maximum Pirate rep needed for the pirate to have this
	 *            laser
	 * @param maxTraderRep
	 *            The maximum Trader rep needed for the trader to have this
	 *            laser
	 */
	private LaserType(int bd, int p, int mtl, int minPoliceRep,
			int minPirateRep, int minTraderRep, int maxPoliceRep,
			int maxPirateRep, int maxTraderRep) {
		baseDamage = bd;
		price = p;
		minTechLevel = mtl;
		this.minPoliceRep = minPoliceRep;
		this.minPirateRep = minPirateRep;
		this.minTraderRep = minTraderRep;
		this.maxPoliceRep = maxPoliceRep;
		this.maxPirateRep = maxPirateRep;
		this.maxTraderRep = maxTraderRep;
	}

	/**
	 * Returns the damage done by the laser
	 * 
	 * @return The damage done by the laser
	 */
	public int getBaseDamage() {
		return baseDamage;
	}

	/**
	 * Gets the minimum tech level for the laser
	 * 
	 * @return The minimum tech level for the laser
	 */
	public int getMinTechLevel() {
		return minTechLevel;
	}

	/**
	 * Returns the price of the laser
	 * 
	 * @return The price of the laser
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns the minimum reputation for the corresponding EncounterType
	 * 
	 * @param type
	 *            The type of NPCEncounter
	 * @return The corresponding minimum reputation for this EncounterType
	 */
	public int getMinRep(EncounterType type) {
		if (type == EncounterType.POLICE)
			return minPoliceRep;
		else if (type == EncounterType.PIRATE)
			return minPirateRep;
		else if (type == EncounterType.TRADER)
			return minTraderRep;
		return 0;
	}

	/**
	 * Returns the maximum reputation for the corresponding EncounterType
	 * 
	 * @param type
	 *            The type of NPCEncounter
	 * @return The corresponding maximum reputation for this EncounterType
	 */
	public int getMaxRep(EncounterType type) {
		if (type == EncounterType.POLICE)
			return maxPoliceRep;
		else if (type == EncounterType.PIRATE)
			return maxPirateRep;
		else if (type == EncounterType.TRADER)
			return maxTraderRep;
		return 0;
	}

	/**
	 * Returns the valid LaserType from the passed in string value
	 * 
	 * @param value
	 *            The string representation of the needed LaserType
	 * @return The LaserType that matches the given string value
	 */
	public static LaserType getEnum(String value) {
		value = value.toLowerCase();
		switch (value) {
		case "beam laser":
			return LaserType.BEAMLASER;
		case "military laser":
			return LaserType.MILITARYLASER;
		case "pulse laser":
			return LaserType.PULSELASER;
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		switch (this) {
		case PULSELASER:
			return "Pulse Laser";
		case BEAMLASER:
			return "Beam Laser";
		case MILITARYLASER:
			return "Military Laser";
		default:
			return null;
		}
	}

	@Override
	public boolean canBuy(Ship ship) {
		if (ship.getNumLasers() + 1 > ship.getShipType().getWeaponSlots()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean canSell(Ship ship) {
		return ship.hasLaser(this);
	}

	@Override
	public int getType() {
		return 1;
	}
}