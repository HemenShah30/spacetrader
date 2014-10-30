package model.Enum;

import model.Sellable;
import model.Ship;

/**
 * Enum representing the various shields
 * 
 * @author Caroline Zhang
 *
 */
public enum ShieldType implements Sellable {

	ENERGYSHIELD(50, 10000, 4, 20, 30, 30, 80, 101, 101), REFLECTIVESHIELD(100,
			35000, 5, 40, 50, 50, 101, 101, 101);

	private int shieldHP;
	private int minTechLevel;
	private double price;
	private int minPoliceRep;
	private int minPirateRep;
	private int minTraderRep;
	private int maxPoliceRep;
	private int maxPirateRep;
	private int maxTraderRep;

	/**
	 * Constructor for the shield enums
	 * 
	 * @param d
	 *            The damage dealt by the shield
	 * @param p
	 *            The price of the shield
	 * @param mtl
	 *            The minimum tech level required for a shield
	 * @param minPoliceRep
	 *            The minimum police rep needed for the police to have this
	 *            shield
	 * @param minPirateRep
	 *            The minimum pirate rep needed for the pirate to have this
	 *            shield
	 * @param minTraderRep
	 *            The minimum trader rep needed for the trader to have this
	 *            shield
	 * @param maxPoliceRep
	 *            The maximum police rep needed for the police to have this
	 *            shield
	 * @param maxPirateRep
	 *            The maximum pirate rep needed for the pirate to have this
	 *            shield
	 * @param maxTraderRep
	 *            The maximum trader rep needed for the trader to have this
	 *            shield
	 */
	private ShieldType(int hp, int p, int mtl, int minPoliceRep,
			int minPirateRep, int minTraderRep, int maxPoliceRep,
			int maxPirateRep, int maxTraderRep) {
		shieldHP = hp;
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
	 * Returns the additional hp contributed by the shield
	 * 
	 * @return The additional hp contributed by the shield
	 */
	public int getShieldHP() {
		return shieldHP;
	}

	/**
	 * Gets the minimum tech level for the shield
	 * 
	 * @return The minimum tech level for the shield
	 */
	public int getMinTechLevel() {
		return minTechLevel;
	}

	/**
	 * Returns the price of the shield
	 * 
	 * @return The price of the shield
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

	@Override
	public String toString() {
		switch (this) {
		case ENERGYSHIELD:
			return "Energy Shield";
		case REFLECTIVESHIELD:
			return "Reflective Shield";
		default:
			return null;
		}
	}

	/**
	 * Returns whether or not the ship can hold another shield
	 * 
	 * @param ship
	 *            the player's ship
	 * @return boolean result
	 */
	@Override
	public boolean canBuy(Ship ship) {
		if (ship.getNumShields() + 1 > ship.getShipType().getShieldSlots()) {
			return false;
		}
		return true;
	}

	/**
	 * Returns whether or not the ship can sell a shield
	 * 
	 * @param ship
	 *            the player's ship
	 * @return boolean result
	 */
	@Override
	public boolean canSell(Ship ship) {
		if (ship.getNumShields() < 1) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a 0 for addSellable purposes
	 * 
	 * @return int type 0 == shield
	 */
	@Override
	public int getType() {
		return 0;
	}
}